package ru.health.assistance.presentation.commons;

@FunctionalInterface
public interface ItemIndexClickListener<T> {
    void onItemClick(T item, int index);
}
