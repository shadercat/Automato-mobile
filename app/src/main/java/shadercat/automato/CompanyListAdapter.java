package shadercat.automato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.CompanyViewHolder> {

    private List<Company> companies;
    private LayoutInflater inflater;
    private ActionHandler mHandler;

    public CompanyListAdapter(Context context, List<Company> companies) {
        this.inflater = LayoutInflater.from(context);
        this.companies = companies;
    }


    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.company_item, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        if (position != companies.size()) {
            Company comp = companies.get(holder.getAdapterPosition());
            holder.name.setText(comp.getName());
            holder.email.setText(comp.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public void setList(List<Company> companies) {
        this.companies = companies;
        notifyDataSetChanged();
    }

    public void setOnClickListeners(ActionHandler handler) {
        this.mHandler = handler;
    }

    interface ActionHandler {
        void onClick(int position);
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView name, email;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.comp_name);
            email = itemView.findViewById(R.id.comp_email);
        }

        @Override
        public void onClick(View v) {
            if (mHandler != null) {
                mHandler.onClick(getLayoutPosition());
            }
        }
    }
}
