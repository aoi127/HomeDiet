<template>
	<view class="index">
		<view class="blue-bg"></view>
		<view class="text-title">Home Diet</view>
		<view class="text-subtitle"> 关 心 健 康 更 关 心 你 </view>
		<view class="nutrition">
			<view class="label1">今日营养，动态规划</view>
			<view class="label2">最个性的营养来自于个性的数据</view>
			<image class='img1' mode="aspectFit" src="../../static/index/food1.jpg"></image>
		</view>
		<view class="recipe">
			<view class="label1">食谱推荐</view>
			<view class="label2">自然 & 健康</view>
			<image class='img1' mode="aspectFit" src="../../static/index/food2.jpg"></image>
		</view>
		<view class="calorie">
			<view class="label1">热量查询</view>
			<view class="label2">丰富 & 精准</view>
			<image class="img1" mode="aspectFit" src="../../static/index/food3.jpg"></image>
		</view>
		<view class="label-sug">为你推荐</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		onLoad() {},
		components: {},
		methods: {}
	}
</script>

<style lang="scss">
	.index {
		background-color: #FFFFFF;
	}
	
	.blue-bg {
		width: 750rpx;
		height: 366rpx;
		background-color: rgba(186, 231, 255, 0.5);
	}

	.text-title {
		font-family: OPPOSans;
		font-weight: 400;
		font-size: 56rpx;
		color: rgb(16, 16, 16);
		font-style: normal;
		letter-spacing: 0rpx;
		line-height: 74rpx;
		text-decoration: none;
		z-index: 1;
		position: absolute;
		left: 50rpx;
		top: 20rpx;
	}

	.text-subtitle {
		font-family: OPPOSans;
		font-weight: 700;
		font-size: 28rpx;
		color: rgb(16, 16, 16);
		font-style: normal;
		letter-spacing: 13rpx;
		line-height: 40rpx;
		text-decoration: none;
		z-index: 1;
		position: absolute;
		left: 50rpx;
		top: 100rpx;
	}

	.nutrition {
		width: 688rpx;
		height: 272rpx;
		background-color: rgb(0, 80, 179);
		border-style: none;
		border-color: unset;
		box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
		border-radius: 20rpx;
		font-size: 28rpx;
		padding: 0rpx;
		text-align: center;
		line-height: 40rpx;
		font-weight: normal;
		font-style: normal;
		z-index: 1;
		position: absolute;
		left: 30rpx;
		top: 180rpx;

		.label1 {
			font-family: PingFangSC;
			font-weight: 400;
			font-size: 40rpx;
			color: rgb(251, 251, 251);
			font-style: normal;
			letter-spacing: 4rpx;
			line-height: 56rpx;
			text-decoration: none;
			z-index: 2;
			position: absolute;
			left: 30rpx;
			top: 50rpx;
		}

		.label2 {
			font-family: PingFangSC;
			font-weight: 400;
			font-size: 24rpx;
			color: rgb(251, 251, 251);
			font-style: normal;
			letter-spacing: 4rpx;
			line-height: 34rpx;
			text-decoration: none;
			z-index: 2;
			position: absolute;
			left: 30rpx;
			top: 130rpx;
		}

		.img1 {
			width: 280rpx;
			height: 226rpx;
			z-index: 2;
			position: absolute;
			left: 430rpx;
			top: -40rpx;
		}
	}

	.recipe {
		width: 330rpx;
		height: 200rpx;
		background-color: rgb(105, 192, 255);
		border-style: none;
		border-color: unset;
		box-shadow: rgb(145, 213, 255) 0px 2px 6px 0px;
		border-radius: 20rpx;
		font-size: 28rpx;
		padding: 0px;
		text-align: center;
		line-height: 40rpx;
		font-weight: normal;
		font-style: normal;
		z-index: 1;
		position: absolute;
		left: 30rpx;
		top: 500rpx;

		.label1 {
			font-family: PingFangSC;
			font-weight: 700;
			font-size: 40rpx;
			color: rgb(251, 251, 251);
			font-style: normal;
			letter-spacing: 0px;
			line-height: 56rpx;
			text-decoration: none;
			z-index: 2;
			position: absolute;
			left: 30rpx;
			top: 30rpx;
		}

		.label2 {
			font-family: PingFangSC;
			font-weight: 400;
			font-size: 28rpx;
			color: rgb(251, 251, 251);
			font-style: normal;
			letter-spacing: 0px;
			line-height: 40rpx;
			text-decoration: none;
			z-index: 2;
			position: absolute;
			left: 30rpx;
			top: 100rpx;
		}

		.img1 {
			width: 130rpx;
			height: 250rpx;
			transform: rotate(15deg);
			z-index: 2;
			position: absolute;
			left: 210rpx;
			top: -40rpx;
		}
	}

	.calorie {
		width: 330rpx;
		height: 200rpx;
		background-color: rgb(149, 222, 100);
		border-style: none;
		border-color: unset;
		box-shadow: rgb(217, 247, 190) 0px 2px 6px 0px;
		border-radius: 20rpx;
		font-size: 28rpx;
		padding: 0px;
		text-align: center;
		line-height: 40rpx;
		font-weight: normal;
		font-style: normal;
		z-index: 1;
		position: absolute;
		left: 388rpx;
		top: 500rpx;

		.label1 {
			font-family: PingFangSC;
			font-weight: 700;
			font-size: 40rpx;
			color: rgb(251, 251, 251);
			font-style: normal;
			letter-spacing: 0px;
			line-height: 56rpx;
			text-decoration: none;
			z-index: 2;
			position: absolute;
			left: 30rpx;
			top: 30rpx;
		}

		.label2 {
			font-family: PingFangSC;
			font-weight: 400;
			font-size: 28rpx;
			color: rgb(251, 251, 251);
			font-style: normal;
			letter-spacing: 0px;
			line-height: 40rpx;
			text-decoration: none;
			z-index: 2;
			position: absolute;
			left: 30rpx;
			top: 100rpx;
		}

		.img1 {
			width: 130rpx;
			height: 250rpx;
			transform: rotate(15deg);
			z-index: 2;
			position: absolute;
			left: 210rpx;
			top: -40rpx;
		}
	}

	.label-sug {
		margin-top: 380rpx;
		margin-left: 30rpx;
		font-family: PingFangSC;
		font-weight: 700;
		font-size: 40rpx;
		color: rgb(16, 16, 16);
		font-style: normal;
		letter-spacing: 0px;
		line-height: 56rpx;
		text-decoration: none;
	}
	
</style>
