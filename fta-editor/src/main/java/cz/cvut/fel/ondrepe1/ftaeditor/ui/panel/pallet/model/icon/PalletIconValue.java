package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.pallet.model.icon;

import cz.cvut.fel.ondrepe1.ftaeditor.common.image.ImageHolder;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.StringValue;

/**
 *
 * @author ondrepe
 */
public class PalletIconValue implements IconValue{

    private StringValue keyToFileName;
    private Map<Object, Icon> iconCache;
    private Icon fallbackIcon;

    public PalletIconValue(StringValue sv) {
        iconCache = new HashMap<Object, Icon>();
        this.keyToFileName = sv;
        fallbackIcon = ImageHolder.getCrossIcon();
    }

    @Override
    public Icon getIcon(Object value) {
        String key = keyToFileName.getString(value);
        Icon icon = iconCache.get(key);
        if (icon == null) {
            icon = loadIcon(key);
        }
        if (icon == null) {
            icon = fallbackIcon;
        }
        return icon;
    }

    private Icon loadIcon(String key) {
        Icon icon = ImageHolder.loadFromSvgResource(key, false);
        if (icon != null) {
            iconCache.put(key, icon);
        }
        return icon;
    }
}
