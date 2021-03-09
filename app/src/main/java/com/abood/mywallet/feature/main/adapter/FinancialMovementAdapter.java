package com.abood.mywallet.feature.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abood.mywallet.databinding.ItemFinancialMovementBinding;
import com.abood.mywallet.model.FinancialMovement;
import com.abood.mywallet.utils.UIUtils;

import java.util.ArrayList;

public class FinancialMovementAdapter extends RecyclerView.Adapter<FinancialMovementAdapter.FinancialMovementHolder> {

    private final Context context;
    private final ArrayList<FinancialMovement> financialMovements;

    public FinancialMovementAdapter(Context context) {
        this.context = context;
        financialMovements = new ArrayList<>();
    }

    @NonNull
    @Override
    public FinancialMovementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FinancialMovementHolder(ItemFinancialMovementBinding
                .inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialMovementHolder holder, int position) {
        holder.data(financialMovements.get(position));
    }

    @Override
    public int getItemCount() {
        return financialMovements.size();
    }

    public void addItem(FinancialMovement financialMovement) {
        financialMovements.add(financialMovement);
        notifyItemInserted(getItemCount() - 1);
    }

    public void clear() {
        financialMovements.clear();
        notifyDataSetChanged();
    }

    class FinancialMovementHolder extends RecyclerView.ViewHolder {

        private final ItemFinancialMovementBinding binding;

        public FinancialMovementHolder(ItemFinancialMovementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void data(FinancialMovement financialMovement) {
            binding.tvId.setText(("#" + financialMovement.getId()));
            binding.tvTime.setText((UIUtils.getDate(financialMovement.getCreate_at()) + " - "
                    + UIUtils.getTime(financialMovement.getCreate_at())));
            binding.tvAmount.setText((financialMovement.getValue() + " " + financialMovement.getCurrency()));
        }
    }
}
