package com.github.yangkl.x.android.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {

    /**
     * 判断权限是否已经获得
     *
     * @param permission
     * @return
     */
    fun isPermissionGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * 申请权限，调用此方法的Activity是在@Code{onRequestPermissionsResult}中处理结果
     *
     * @param activity
     * @param permission
     * @param reqCode
     */
    fun requestPermissions(activity: Activity?, permission: Array<String?>?, reqCode: Int) {
        if (activity != null && permission != null) {
            ActivityCompat.requestPermissions(activity, permission, reqCode)
        }
    }

    /**
     * 判断权限是否被用户设置为“不再显示”，及永远不会授权
     *
     * @param activity
     * @param permission
     * @return
     */
    fun isRefuseForever(activity: Activity?, permission: String?): Boolean {
        // 只有当用户同时点选了拒绝开启权限和不再提醒后才会返回true
        if (activity != null && permission != null) {
            return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
        }
        return false
    }
}