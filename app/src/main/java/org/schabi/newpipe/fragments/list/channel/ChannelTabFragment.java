package org.schabi.newpipe.fragments.list.channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.schabi.newpipe.R;
import org.schabi.newpipe.error.UserAction;
import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.channel.ChannelTabInfo;
import org.schabi.newpipe.extractor.linkhandler.ChannelTabHandler;
import org.schabi.newpipe.fragments.list.BaseListInfoFragment;
import org.schabi.newpipe.util.Constants;
import org.schabi.newpipe.util.ExtractorHelper;

import icepick.State;
import io.reactivex.rxjava3.core.Single;

public class ChannelTabFragment extends BaseListInfoFragment<InfoItem, ChannelTabInfo> {

    @State
    protected int serviceId = Constants.NO_SERVICE_ID;

    @State
    protected ChannelTabHandler tabHandler;

    public static ChannelTabFragment getInstance(final int serviceId,
                                                 final ChannelTabHandler tabHandler) {
        final ChannelTabFragment instance = new ChannelTabFragment();
        instance.serviceId = serviceId;
        instance.tabHandler = tabHandler;
        return instance;
    }

    public ChannelTabFragment() {
        super(UserAction.REQUESTED_CHANNEL);
    }

    /*//////////////////////////////////////////////////////////////////////////
    // LifeCycle
    //////////////////////////////////////////////////////////////////////////*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_channel_tab, container, false);
    }

    @Override
    protected Single<ChannelTabInfo> loadResult(final boolean forceLoad) {
        return ExtractorHelper.getChannelTab(serviceId, tabHandler, forceLoad);
    }

    @Override
    protected Single<ListExtractor.InfoItemsPage<InfoItem>> loadMoreItemsLogic() {
        return ExtractorHelper.getMoreChannelTabItems(serviceId, tabHandler, currentNextPage);
    }

    @Override
    public void setTitle(final String title) {
    }
}
