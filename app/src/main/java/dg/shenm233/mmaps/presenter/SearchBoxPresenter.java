/*
 * Copyright 2016 Shen Zhang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dg.shenm233.mmaps.presenter;

import android.content.Context;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Tip;

import java.util.List;

import dg.shenm233.mmaps.util.AMapUtils;

public class SearchBoxPresenter {
    private Context mContext;
    private ISearchBox mSearchBox;
    private MapsModule mMapsModule;

    private Inputtips mInputTips;

    public SearchBoxPresenter(Context context, ISearchBox searchBox, MapsModule mapsModule) {
        mContext = context;
        mSearchBox = searchBox;
        mMapsModule = mapsModule;
    }

    public void requestInputTips(String string, String city) {
        if (mInputTips == null)
            mInputTips = new Inputtips(mContext, inputTipsListener);
        try {
            mInputTips.requestInputtips(string, city);
        } catch (AMapException e) {
            //
        }
    }

    private Inputtips.InputtipsListener inputTipsListener =
            new Inputtips.InputtipsListener() {
                @Override
                public void onGetInputtips(List<Tip> list, int rCode) {
                    if (rCode == AMapUtils.AMAP_CORE_SUCCESS) {
                        mSearchBox.onGetInputTips(list);
                    }
                }
            };
}