package ru.health.assistance.presentation.commons;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class DiffUtillCallback<T> extends DiffUtil.Callback {

    protected List<T> oldItems;
    protected List<T> newItems;

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return equals(oldItemPosition, newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return equals(oldItemPosition, newItemPosition);
    }

    public void setItems(List<T> oldItems, List<T> newItems) {
        this.oldItems = oldItems;
        this.newItems = newItems;
    }

//    protected abstract boolean compareItem(int oldItemPosition, int newItemPosition);
//    protected abstract boolean compareContent(int oldItemPosition, int newItemPosition);

    private boolean equals(int oldItemPosition, int newItemPosition){
        if (oldItemPosition > oldItems.size()) return false;
        if (newItemPosition > newItems.size()) return false;
        T oldItem = oldItems.get(oldItemPosition);
        T newItem = newItems.get(newItemPosition);
        return oldItem.equals(newItem);
    }

}
