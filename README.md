# kotlin-mvp-demo
android-kotlin-mvp-demo,封装了刷新加载分页等操作

如果你的页面中包含了列表，这个列表需要下拉刷新以及上拉加载分页获取数据这样的功能，你只要继承了BaseListMvpActivity或BaseListMvpFragment,实现响应的方法即可。
这样的话，每一个页面你都不需要考虑下拉刷新的回调，对数据处理，网络成功后对刷新页面的状态恢复，空数据的情况处理显示，网络失败等等的一系列操作。
