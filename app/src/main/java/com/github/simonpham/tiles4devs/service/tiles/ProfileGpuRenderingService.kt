package com.github.simonpham.tiles4devs.service.tiles

import android.os.SystemProperties
import android.service.quicksettings.Tile
import com.github.simonpham.tiles4devs.SYSPROP_DEBUG_GPU_PROFILE
import com.github.simonpham.tiles4devs.kickSystemService
import com.github.simonpham.tiles4devs.service.BaseTileService

/**
 * Created by Simon Pham on 5/31/18.
 * Email: simonpham.dn@gmail.com
 */

class ProfileGpuRenderingService : BaseTileService() {
    override fun refresh() {
        val enabled = SystemProperties.get(SYSPROP_DEBUG_GPU_PROFILE, "false") == "visual_bars"
        qsTile.state = if (enabled) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onClick() {
        SystemProperties.set(SYSPROP_DEBUG_GPU_PROFILE,
                if (qsTile.state == Tile.STATE_INACTIVE) "visual_bars" else "false")
        kickSystemService() // Settings app magic
        refresh()
    }
}