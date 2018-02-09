package de.com.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import de.com.AppController;
import de.com.R;
import de.com.model.pojo.CountryDetail;

/**
 * Created by Ramana on 07-02-2018.
 */
public class DisplayListAdapter extends RecyclerView.Adapter<DisplayListAdapter.ViewHolder> {
    Context context;


    private List<CountryDetail> mDataset;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText,descText;
        public NetworkImageView mNetworkImageView;


        public ViewHolder(View v) {
            super(v);
            titleText = (TextView) v.findViewById(R.id.title_text);
            descText = (TextView) v.findViewById(R.id.description);
            mNetworkImageView = (NetworkImageView)v. findViewById(R.id
                    .ntworkImage);

        }
    }

    public DisplayListAdapter(Context context, List<CountryDetail> myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_card, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if ((mDataset.get(position).getTitle() != null)) {
            if (!mDataset.get(position).getTitle().isEmpty()) {
                holder.titleText.setText(mDataset.get(position).getTitle());
            } else {
                holder.titleText.setText("No Data");
            }

        }
        if ((mDataset.get(position).getDescription() != null)) {
            if (!mDataset.get(position).getDescription().isEmpty()) {
                holder.descText.setText(mDataset.get(position).getDescription());
            } else {
                holder.descText.setText("No Data");
            }
        }
        imageLoader = AppController.getInstance().getImageLoader();
        holder.mNetworkImageView.setImageUrl(mDataset.get(position).getImageHref(), imageLoader);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    public CountryDetail getItemAtPosition(int position){
       return mDataset.get(position);
    }
}
