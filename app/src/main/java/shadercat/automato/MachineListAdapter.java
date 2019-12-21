package shadercat.automato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MachineListAdapter extends RecyclerView.Adapter<MachineListAdapter.MachineViewHolder> {
    private static final int OK_LAYOUT = 1;
    private static final int WARNING_LAYOUT = 2;
    private LayoutInflater inflater;
    private List<Machine> machines;
    private ActionHandler mHandler;

    MachineListAdapter(Context context, List<Machine> machines) {
        this.machines = machines;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MachineListAdapter.MachineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case OK_LAYOUT:
                view = inflater.inflate(R.layout.machine_item_ok, parent, false);
                return new MachineViewHolder(view);
            case WARNING_LAYOUT:
                view = inflater.inflate(R.layout.machine_item_warning, parent, false);
                return new MachineViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MachineListAdapter.MachineViewHolder holder, int position) {
        if (position != machines.size()) {
            Machine mac = machines.get(holder.getAdapterPosition());
            holder.macName.setText(mac.getName());
            holder.macId.setText(mac.getMacId());
            holder.prodState.setText(mac.getProdState());
            holder.state.setText(mac.getState());
        }
    }

    @Override
    public int getItemCount() {
        return machines == null ? 0 : machines.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (machines.get(position).getProdState().equals("normal")) ? OK_LAYOUT : WARNING_LAYOUT;
    }

    public void setOnClickListener(ActionHandler listener) {
        mHandler = listener;
    }

    public void setList(List<Machine> machines) {
        this.machines = machines;
        notifyDataSetChanged();
    }

    interface ActionHandler {
        void OnClick(int position);
    }

    public class MachineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView macName, macId, state, prodState;

        public MachineViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            macName = itemView.findViewById(R.id.mac_name);
            macId = itemView.findViewById(R.id.mac_id);
            state = itemView.findViewById(R.id.mac_state);
            prodState = itemView.findViewById(R.id.mac_prod_state);
        }

        @Override
        public void onClick(View v) {
            if (mHandler != null) {
                mHandler.OnClick(getLayoutPosition());
            }
        }
    }
}
