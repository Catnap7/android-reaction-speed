package com.main.reactionspeed.utils

import com.main.reactionspeed.R


fun Int.formatSeconds(): Double {
    return (this / 1000.0)
}

fun getPercent(score: Int): String {
    return when (score) {
        in 0..110 -> "0.94"
        in 110..120 -> "2.12"
        in 120..130 -> "3.62"
        in 130..140 -> "5.92"
        in 140..150 -> "9.47"
        in 150..160 -> "14.80"
        in 160..190 -> "22.05"
        in 190..250 -> "30.70"
        in 250..270 -> "39.80"
        in 270..290 -> "49.00"
        in 290..310 -> "58.00"
        in 310..330 -> "67.00"
        in 330..350 -> "77.87"
        in 350..370 -> "84.25"
        in 370..390 -> "88.80"
        in 390..410 -> "91.90"
        in 410..450 -> "96.52"
        else -> "100.00"
    }
}

fun getTierImageResource(score: Int): Int {
    return when (score) {
        in 0..45 -> R.drawable.tier_challenger
        in 45..60 -> R.drawable.tier_grandmaster
        in 60..100 -> R.drawable.tier_master
        in 100..130 -> R.drawable.tier_diamond
        in 130..160 -> R.drawable.tier_platinum
        in 160..270 -> R.drawable.tier_gold
        in 270..330 -> R.drawable.tier_silver
        in 330..450 -> R.drawable.tier_bronze
        else -> R.drawable.tier_iron
    }
}

enum class Tier(val score: IntRange, val tier: String, val drawableRes: Int) {
    CHALLENGER(0 .. 45, "챌린저", R.drawable.tier_challenger),
    GRANDMASTER(45 .. 60, "그랜드마스터", R.drawable.tier_grandmaster),
    MASTER(60 .. 100, "마스터", R.drawable.tier_master),
    DIAMOND(100 .. 130, "다이아몬드", R.drawable.tier_diamond),
    PLATINUM(130 .. 160, "플래티넘", R.drawable.tier_platinum),
    GOLD(160 .. 270, "골드", R.drawable.tier_gold),
    SILVER(270 .. 330, "실버", R.drawable.tier_silver),
    BRONZE(330 .. 450, "브론즈", R.drawable.tier_bronze),
    IRON(450 .. 1000, "아이언", R.drawable.tier_iron);

    companion object {
        fun getTier(score: Int): Tier {
            return entries.firstOrNull { it.score.contains(score) } ?: IRON
        }
    }
}

