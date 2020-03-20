package com.test.downloadtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.downloadtest.bean.DownloadFileInfo;
import com.test.downloadtest.service.DownloadService;

import java.util.List;

/**
 * @author DavidZXY
 * @date 2020/1/10
 */
public class FileDownloadRecycleAdapter extends RecyclerView.Adapter<FileDownloadRecycleAdapter.ViewHolder> {

    private Context mContext;
    private List<DownloadFileInfo> mInfoList;

    FileDownloadRecycleAdapter(Context context, List<DownloadFileInfo> infoList) {
        mContext = context;
        mInfoList = infoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DownloadFileInfo fileInfo = mInfoList.get(position);
        holder.mFileNameTextView.setText(fileInfo.getFileName());
        holder.mControlButton.setOnClickListener(v -> {
            String control = holder.mControlButton.getText().toString();
            if ("开始".equals(control)) {
                holder.mControlButton.setText("暂停");
                Intent intent = new Intent(mContext, DownloadService.class);
                intent.setAction(DownloadService.ACTION_START);
                intent.putExtra("fileInfo", fileInfo);
                mContext.startService(intent);
            } else {
                holder.mControlButton.setText("开始");
                Intent intent = new Intent(mContext, DownloadService.class);
                intent.setAction(DownloadService.ACTION_STOP);
                intent.putExtra("fileInfo", fileInfo);
                mContext.startService(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mFileNameTextView;
        ProgressBar mProgressBar;
        Button mControlButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mFileNameTextView = itemView.findViewById(R.id.text_view);
            mProgressBar = itemView.findViewById(R.id.progress_bar);
            mControlButton = itemView.findViewById(R.id.control_button);
        }
    }
}
