package ru.health.assistance.presentation.history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.health.assistance.R;
import ru.health.assistance.data.dto.InfoDTO;
import ru.health.assistance.presentation.commons.BaseRecyclerViewAdapter;
import ru.health.assistance.presentation.commons.DiffUtillCallback;
import ru.health.assistance.presentation.commons.ItemClickListener;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class HistoryFragment extends Fragment implements HistoryView{

    @Inject DefaultHistoryPresenter pres;

    @BindView(R.id.recyclerview) RecyclerView recyclerview;

    private View root;
    private Unbinder binder;

    private HistoryAdapter adapter;

    public static Fragment getInstance(Bundle args){
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args==null? new Bundle(): args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_history, container, false);
        binder = ButterKnife.bind(this, root);
        pres.bindView(this);

        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(lm);
        adapter= new HistoryAdapter(getContext(), new ArrayList<>(),
                new DiffUtillCallback<>(), item -> pres.onItemClicked(item));
        recyclerview.setAdapter(adapter);

        pres.onLoad();

        return root;
    }

    @Override
    public void onDestroy() {
        binder.unbind();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        pres.unbindView();
        super.onDestroyView();
    }

    @Override
    public void showHistories(List<InfoDTO> infoDTO) {
        adapter.setItems(infoDTO);
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showInfo(InfoDTO item) {
        if (getActivity() instanceof OnShowInfoCallback){
            ((OnShowInfoCallback)getActivity()).showInfoCallback(item);
        }
    }

    private class HistoryAdapter extends BaseRecyclerViewAdapter<InfoDTO>{

        HistoryAdapter(Context context, List<InfoDTO> items, DiffUtillCallback<InfoDTO> diffCallback, ItemClickListener<InfoDTO> itemClickListener) {
            super(context, items, diffCallback, itemClickListener);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.row_history, parent, false);
            return new InfoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            InfoDTO item = items.get(position);
            InfoViewHolder info = (InfoViewHolder)holder;

            info.fathername.setText(item.getFatherName());
            info.firstname.setText(item.getName());
            info.lastname.setText(item.getLastName());

            int productImageId = getContext().getResources().getIdentifier(item.getId(), "drawable", getContext().getPackageName());
            Glide.with(getContext()).load(productImageId).into(info.avatar);

            info.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }

    }

    class InfoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageview_photo) CircleImageView avatar;

        @BindView(R.id.textview_lastname) TextView lastname;
        @BindView(R.id.textview_firstname) TextView firstname;
        @BindView(R.id.textview_fathername) TextView fathername;

        InfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public interface OnShowInfoCallback{
        void showInfoCallback(InfoDTO item);
    }
}
