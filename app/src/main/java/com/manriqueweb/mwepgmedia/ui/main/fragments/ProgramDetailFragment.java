package com.manriqueweb.mwepgmedia.ui.main.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.manriqueweb.mwepgmedia.R;
import com.manriqueweb.mwepgmedia.model.Program;
import com.manriqueweb.mwepgmedia.network.IApiResponse;
import com.manriqueweb.mwepgmedia.network.NetworkWrapper;
import com.manriqueweb.mwepgmedia.ui.commons.BaseFragment;
import com.manriqueweb.mwepgmedia.utils.Constants;
import com.squareup.picasso.Picasso;

public class ProgramDetailFragment extends BaseFragment {
    //region App Variables
    private static final String LOG_TAG = Constants.STR_LOG_TAG.concat(ProgramDetailFragment.class.getSimpleName());

    private OnProgramDetailFragmentListener mOnProgramDetailFragmentListener = null;

    private View mProgressView;
    private ImageView iv_mainImage;
    private TextView tv_title, tv_description;
    private String program_id;


    //endregion

    //region Constructor
    public ProgramDetailFragment() {
    }
    //endregion

    //region Fragment methods
    public static ProgramDetailFragment newInstance() {
        return new ProgramDetailFragment();
    }

    @Override
    protected int getLayoutId() {
        if(isDebbud()){
            Log.d(LOG_TAG, "getLayoutId(): R.layout.fragment_program_details");
        }
        return R.layout.fragment_program_details;
    }

    @Override
    public String getCustomTag() {
        return ProgramDetailFragment.class.getSimpleName();
    }

    @Override
    public void onStart() {
        super.onStart();

        if(isDebbud()){
            Log.d(LOG_TAG, "onStart getSimpleName(): " + getCustomTag());
        }

        drawProgramDetail();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mProgressView = mView.findViewById(R.id.pb_program);
        iv_mainImage = mView.findViewById(R.id.iv_mainImage);
        tv_title = mView.findViewById(R.id.tv_title);
        tv_description = mView.findViewById(R.id.tv_description);

        final Button bt_play = mView.findViewById(R.id.bt_play);
        final Button bt_cancel = mView.findViewById(R.id.bt_cancel);

        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnProgramDetailFragmentListener!=null){
                    mOnProgramDetailFragmentListener.onPlaySelected();
                }
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnProgramDetailFragmentListener!=null){
                    mOnProgramDetailFragmentListener.onBackSelected();
                }
            }
        });

        return mView;
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }
    //endregion

    //region public method
    public void setProgram_id(String program_id) {
        this.program_id = program_id;
    }

    public void drawProgramDetail(){
        showProgress(true);

        if(isDebbud()){
            Log.d(LOG_TAG, "program_id: "+program_id);
        }

        if(program_id!=null){
            NetworkWrapper.getInstance().getProgramDataAsync(program_id, new IApiResponse<Program>() {
                @Override
                public void onResponse(Program data) {
                    if(isDebbud()){
                        Log.d(LOG_TAG, "Program: "+data.getTitle()+", Images Logo: "+data.getImages().getIcon());
                    }

                    drawProgramDetailImplement(data);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
    }

    private void drawProgramDetailImplement(Program mProgram){
        tv_title.setText(mProgram.getTitle());
        tv_description.setText(mProgram.getDescription());

        Picasso.with(getContext())
                .load(mProgram.getImages().getIcon())
                .fit().centerInside()
                .error(android.R.drawable.ic_menu_help)
                .into(iv_mainImage);

        showProgress(false);
    }

    //endregion

    //region Listener methods
    public void setOnProgramDetailFragmentListener(OnProgramDetailFragmentListener onProgramDetailFragmentListener) {
        mOnProgramDetailFragmentListener = onProgramDetailFragmentListener;
    }

    public interface OnProgramDetailFragmentListener {
        void onPlaySelected();
        void onBackSelected();
    }
    //endregion
}
