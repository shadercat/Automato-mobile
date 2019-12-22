package shadercat.automato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CompaniesFragment extends Fragment {

    private CompaniesViewModel mViewModel;
    List<Company> companies = new ArrayList<>();
    RecyclerView recyclerView;
    CompanyListAdapter adapter;


    public static CompaniesFragment newInstance() {
        return new CompaniesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.companies_fragment, container, false);
        recyclerView = view.findViewById(R.id.company_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CompaniesViewModel.class);
        adapter = new CompanyListAdapter(getContext(), companies);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        mViewModel.getData().observe(this, new Observer<List<Company>>() {
            @Override
            public void onChanged(List<Company> companiesList) {
                companies = companiesList;
                adapter.setList(companies);
            }
        });

        adapter.setOnClickListeners(new CompanyListAdapter.ActionHandler() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), "click on " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

}
