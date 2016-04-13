package com.amozh.item.model.projection;

import com.amozh.item.model.Item;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Andrii Mozharovskyi on 13.04.2016.
 */
@Projection(name = "m_preview", types = {Item.class})
public interface MinimalPreviewProjection {
    String getName();
}
