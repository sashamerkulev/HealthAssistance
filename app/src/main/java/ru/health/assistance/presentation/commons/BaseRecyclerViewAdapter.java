package ru.health.assistance.presentation.commons;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;


public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final List<T> items;
    protected final LayoutInflater inflater;
    protected final ItemClickListener<T> itemClickListener;

    protected final DiffUtillCallback<T> diffCallback;

    public BaseRecyclerViewAdapter(Context context, List<T> items, DiffUtillCallback<T> diffCallback, ItemClickListener<T> itemClickListener){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.itemClickListener = itemClickListener;
        this.diffCallback = diffCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<T> clients) {
        diffCallback.setItems(items, clients);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.items.clear();
        this.items.addAll(clients);
        diffResult.dispatchUpdatesTo(this);
    }

    public List<T> getItems() {
        return items;
    }

}
