package shadercat.automato;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MachinesFragment extends Fragment implements SnackbarMessage.SnackbarObserver {

    private MachinesViewModel mViewModel;
    List<Machine> machinesList = new ArrayList<>();
    RecyclerView recyclerView;
    MachineListAdapter adapter;

    public static MachinesFragment newInstance() {
        return new MachinesFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.machines_fragment, container, false);
        recyclerView = view.findViewById(R.id.machine_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MachinesViewModel.class);
        adapter = new MachineListAdapter(getContext(), machinesList);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);


        mViewModel.getData().observe(this, new Observer<List<Machine>>() {
            @Override
            public void onChanged(List<Machine> machines) {
                machinesList = machines;
                adapter.setList(machinesList);
            }
        });
        adapter.setOnClickListener(new MachineListAdapter.ActionHandler() {
            @Override
            public void OnClick(int position) {
                Intent mac_info = new Intent(getContext(), MacInformation.class);
                mac_info.putExtra(MacInformation.MAC_ID_EXTRA, machinesList.get(position).getMacId());
                startActivity(mac_info);
            }
        });

    }

    @Override
    public void onNewMessage(int snackbarMessageResourceId) {
        ThematicSnackbar.SnackbarTextShow(getString(snackbarMessageResourceId), recyclerView);
    }
}
