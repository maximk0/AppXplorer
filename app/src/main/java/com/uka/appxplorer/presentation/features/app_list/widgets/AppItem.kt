package com.uka.appxplorer.presentation.features.app_list.widgets

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.uka.appxplorer.R

@Composable
fun AppItem(
    icon: Drawable,
    name: String,
    onAppClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onAppClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = icon,
            contentDescription = stringResource(R.string.app_list_ic_description),
            modifier = Modifier.size(48.dp)
        )
        
        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = name,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
private fun AppItemPreview() {
    AppItem(
        icon = ColorDrawable(Color.Gray.toArgb()),
        name = "AppXplorer",
        onAppClick = {}
    )
}
