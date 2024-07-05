package com.example.myassignment.presentation.activity.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myassignment.R
import com.example.myassignment.data.models.BannerContentList

@Composable
fun DataLists(dataItemUiState: List<BannerContentList>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 3.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.dark_green))
    ) {
        Row(modifier = Modifier.padding(7.dp), verticalAlignment = Alignment.CenterVertically) {
            dataItemUiState.get(0).subBannerImage?.let { painterResource(it) }?.let {
                Image(
                    painter = it, "content description",

                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(7.dp))
                )
            }
            Column(modifier = Modifier.padding(start = 20.dp)) {
                dataItemUiState.get(0).title?.let {
                    Text(
                        text = it,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
                dataItemUiState.get(0).subTitle?.let {
                    Text(
                        text = it,
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}