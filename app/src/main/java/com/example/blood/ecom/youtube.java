package com.example.blood.ecom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Vector;

public class youtube extends AppCompatActivity {
    RecyclerView recyclerView;

    Vector<videoUrl> youtubeVideos = new Vector<videoUrl>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        //Load video List

        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/l--Z7peXwe4?list=PLRCuES4BI4h4PW2ubv_Vwak-6WqtCLHbK\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/3R5CLT1Ui4M?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YRT0Hkxw9w8?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/bGdbBdO8hC8?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Ex3IcCaW5kY?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/v2RVF83QgS0?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_AXuFRrb520?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/oHKLC0AlPH0?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/nJ_XjnKK3N8?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/LI4a2V108DQ?list=PLRCuES4BI4h74zzjJr4ZqSKdFLHGOABg2\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/nGa9A__ByHY?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/V1iFrMLtqwI?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/kI-ELhHO1WY?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/WDoaW2sSZ1k?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7mI0c6UYZ5A?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/FX-JXY41rXs?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mEG9ErgWBiE?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"1005\" src=\"https://www.youtube.com/embed/A1nfeodXG_8?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CKdz9nHyfqw?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/g-WkqaCaE6c?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        youtubeVideos.add(new videoUrl("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Pw5G0oWW4D4?list=PLRCuES4BI4h4vPO5ALz911UhigROTLsbH\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
    }
}
