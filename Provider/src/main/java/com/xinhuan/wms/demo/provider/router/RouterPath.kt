package com.xinhuan.wms.demo.provider.router

object RouterPath {

    //用户中心
    class UserCenter {
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
            const val PATH_SETTING = "/userCenter/setting"
            const val PATH_MODIFY_PWD = "/userCenter/modifyPwd"
        }
    }

    //出库
    class OutWarehouse {
        companion object {
            const val PATH_TASK = "/outWarehouse/task"
            const val PATH_SELECT_CAR_TYPE = "/outWarehouse/selectCarType"
        }
    }

    //入库
    class InWarehouse {
        companion object {
            //商品验收页面
            const val IN_CHECK = "/inWarehouse/inCheck"
        }
    }

    //回库
    class BackWarehouse {
        companion object {
        }
    }

    //库内管理
    class WarehouseManager {
        companion object {
            //货位转移
            const val LOCATION_MOVE = "/warehouseManager/locationMove"
        }
    }

    class App {
        companion object {
            const val MAIN_ACTIVITY = "/app/mainActivity"
        }
    }

    //语音播放
    class VoiceCenter {
        companion object {
            const val PATH_VOICE = "/voiceCenter/speak"
        }
    }

    //查看大图
    class PhotoView{
        companion object{
            const val PATCH_PHOTO_VIEW = "/photoView/photoViewActivity"
        }
    }


}
