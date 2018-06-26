package com.example.designmodal.edevnews.Model;

/**
 * Created by compware on 6/26/2018.
 */

public class AdsModel
{
    private String ads_image,ads_link;

    public AdsModel(String ads_image, String ads_link)
    {
        this.ads_image = ads_image;
        this.ads_link = ads_link;
    }

    public String getAds_image() {
        return ads_image;
    }

    public void setAds_image(String ads_image) {
        this.ads_image = ads_image;
    }

    public String getAds_link() {
        return ads_link;
    }

    public void setAds_link(String ads_link) {
        this.ads_link = ads_link;
    }
}
