package ru.health.assistance.presentation.commons;

@FunctionalInterface
public interface ItemClickListener<T> {
    void onItemClick(T item);
}
