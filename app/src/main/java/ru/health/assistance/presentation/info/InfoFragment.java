package ru.health.assistance.presentation.info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import de.hdodenhof.circleimageview.CircleImageView;
import ru.health.assistance.App;
import ru.health.assistance.R;
import ru.health.assistance.data.dto.InfoDTO;
import ru.health.assistance.presentation.controls.InfoRowView;

/**
 * Created by sasha_merkulev on 17.03.2018.
 */

public class InfoFragment extends Fragment implements InfoView{

    private static final String KEY_BUNDLE_ID = InfoFragment.class.getSimpleName()+".id";

    @Inject DefaultInfoPresenter pres;

    @BindView(R.id.imageview_photo) CircleImageView avatar;

    @BindView(R.id.textview_lastname) TextView lastname;
    @BindView(R.id.textview_firstname) TextView firstname;
    @BindView(R.id.textview_fathername) TextView fathername;
    @BindView(R.id.textview_sex) TextView sex;

    @BindView(R.id.birthdate) InfoRowView birthdate;
    @BindView(R.id.birthplace) InfoRowView birthplace;
    @BindView(R.id.nationality) InfoRowView nationality;
    @BindView(R.id.livingplace) InfoRowView livingplace;
    @BindView(R.id.cause) InfoRowView cause;

    @BindView(R.id.textview_scan) TextView scan;

    private View root;
    private Unbinder binder;
    private String id;

    public static Fragment getInstance(Bundle args){
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args==null? new Bundle(): args);
        return fragment;
    }

    public static Bundle getArgs(String id){
        Bundle args = new Bundle();
        args.putString(KEY_BUNDLE_ID, id);
        return args;
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
        root = inflater.inflate(R.layout.fragment_info, container, false);
        binder = ButterKnife.bind(this, root);
        pres.bindView(this);

        Bundle args = getArguments();
        id = args.getString(KEY_BUNDLE_ID);

        scan.setOnClickListener(view -> getActivity().finish());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        pres.setId(id);
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
    public void showInfo(InfoDTO infoDTO) {

        int productImageId = getContext().getResources().getIdentifier(infoDTO.getId(), "drawable", getContext().getPackageName());
        Glide.with(getContext()).load(productImageId).into(avatar);

        lastname.setText(infoDTO.getLastName());
        firstname.setText(infoDTO.getName());
        fathername.setText(infoDTO.getFatherName());
        sex.setText(infoDTO.getSex());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        birthdate.setInfo(format.format(infoDTO.getBirthDate()));

        birthplace.setInfo(infoDTO.getBirthPlace());
        nationality.setInfo(infoDTO.getNationality());
        livingplace.setInfo(String.format(Locale.getDefault(), "%s, %s\n%s",
                infoDTO.getLivingPostcode(), infoDTO.getLivingCity(), infoDTO.getLivingAddress()));
        cause.setInfo(infoDTO.getCause());
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show();
    }
}
