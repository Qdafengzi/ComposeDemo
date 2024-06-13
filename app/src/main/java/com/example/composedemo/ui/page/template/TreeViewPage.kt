package com.example.composedemo.ui.page.template

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName.HOME
import com.example.composedemo.ui.theme.info
import com.example.composedemo.ui.widget.CommonToolbar
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser


enum class JsonNodeType {
    OBJECT, ARRAY, VALUE
}

data class JsonNode(
    val key: String,
    val value: Any?,
    val type: JsonNodeType,
    val children: List<JsonNode> = emptyList(),
    var isExpanded: Boolean = true
)

fun parseJson(jsonString: String): List<JsonNode> {
    val jsonElement = JsonParser.parseString(jsonString)
    return parseJsonElement(jsonElement, "")
}

fun parseJsonElement(jsonElement: JsonElement, key: String): List<JsonNode> {
    val nodes = mutableListOf<JsonNode>()
    when {
        jsonElement.isJsonObject -> {
            val jsonObject = jsonElement.asJsonObject
            val children = jsonObject.entrySet().flatMap { (k, v) ->
                parseJsonElement(v, k)
            }
            nodes.add(JsonNode(key, null, JsonNodeType.OBJECT, children))
        }

        jsonElement.isJsonArray -> {
            val jsonArray = jsonElement.asJsonArray
            val children = jsonArray.flatMapIndexed { index, element ->
                parseJsonElement(element, "$key[$index]")
            }
            nodes.add(JsonNode(key, null, JsonNodeType.ARRAY, children))
        }

        else -> {
            nodes.add(JsonNode(key, jsonElement.asString, JsonNodeType.VALUE))
        }
    }
    return nodes
}

@Composable
fun JsonTreeView(jsonNode: JsonNode, depth: Int = 0) {
    var isExpanded by remember { mutableStateOf(jsonNode.isExpanded) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = (depth * 16).dp)
            .clickable {
                if (jsonNode.type != JsonNodeType.VALUE) isExpanded = !isExpanded
            }
    ) {
        Text(
            text = when (jsonNode.type) {
                JsonNodeType.OBJECT -> {
                    if (jsonNode.isExpanded){
                        "${if (jsonNode.key.isNotBlank()) "${jsonNode.key}:" else ""} { "
                    } else{
                        "${if (jsonNode.key.isNotBlank()) "${jsonNode.key}:" else ""} { }"
                    }
                }
                JsonNodeType.ARRAY -> {
                    if (jsonNode.isExpanded){
                        "${jsonNode.key}: ["
                    }else{
                        "${jsonNode.key}: []"
                    }
                }
                JsonNodeType.VALUE -> {
                    "${jsonNode.key}: ${jsonNode.value}"
                }
            },
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .background(if (isExpanded) Color.LightGray else Color.Transparent)
                .padding(8.dp)
        )

        if (isExpanded) {
            jsonNode.children.forEach { child ->
                JsonTreeView(jsonNode = child, depth = depth + 1)
            }
        }

        if (isExpanded && (jsonNode.type == JsonNodeType.OBJECT || jsonNode.type == JsonNodeType.ARRAY)) {
            Text(
                text = when (jsonNode.type) {
                    JsonNodeType.OBJECT -> "}"
                    JsonNodeType.ARRAY -> "]"
                    else -> ""
                },
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(start = (depth * 16).dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
fun JsonTreeList(jsonNodes: List<JsonNode>) {
    LazyColumn {
        itemsIndexed(items = jsonNodes, key = { index, item ->
            index
        }, itemContent = { index, jsonNode ->
            JsonTreeView(jsonNode = jsonNode)
        })
    }
}

val jsonString = """
        {
            "root": {
                "child1": {
                    "grandchild1": "value1.1",
                    "grandchild2": "value1.2"
                },
                "child2": ["value2.1", "value2.2"]
            }
        }
    """

/**
 * Created by finn on 2022/5/6
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TreeViewPage(navCtrl: NavHostController, title: String) {
    val jsonNodes = parseJson(jsonString)
    CommonToolbar(navCtrl, title) {
        JsonTreeList(jsonNodes = jsonNodes)
    }
}