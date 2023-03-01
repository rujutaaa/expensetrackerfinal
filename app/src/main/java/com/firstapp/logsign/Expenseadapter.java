package com.firstapp.logsign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Expenseadapter extends RecyclerView.Adapter<ImageViewHolderExpense> {

    private Context context;
    private List<ExpModelClass> imagelist;

    public Expenseadapter(Context context, List<ExpModelClass> imagelist){
        this.context=context;
        this.imagelist=imagelist;
    }

    @NonNull
    @Override
    public ImageViewHolderExpense onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_card,parent,false);
        return new ImageViewHolderExpense(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolderExpense holder, int position) {
        final ExpModelClass temp = imagelist.get(position);

//        holder.expense_type.setText(temp.getExpense_type());
//        holder.amount.setText(temp.getAmount());
//        holder.date.setText(temp.getDate());
    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }
}
class ImageViewHolderExpense extends RecyclerView.ViewHolder {

    TextView expense_type, amount, date;
    ImageView img;

    public ImageViewHolderExpense(@NonNull View itemView) {
        super(itemView);
        expense_type=itemView.findViewById(R.id.expname);
        date=itemView.findViewById(R.id.expdate);
        amount=itemView.findViewById(R.id.expamt);
        img=itemView.findViewById(R.id.expimg);
    }
}
