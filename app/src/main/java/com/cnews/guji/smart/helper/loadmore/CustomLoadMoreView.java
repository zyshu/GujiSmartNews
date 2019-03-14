package com.cnews.guji.smart.helper.loadmore;


//import com.chad.baserecyclerviewadapterhelper.R;
//import com.chad.library.adapter.base.loadmore.LoadMoreView;

import com.cnews.guji.smart.R;
import com.github.library.loadmore.LoadMoreView;

/**
 * Created by BlingBling on 2016/10/15.
 */

public final class CustomLoadMoreView extends LoadMoreView {

    @Override public int getLayoutId() {
        return R.layout.ba_view_load_more;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
