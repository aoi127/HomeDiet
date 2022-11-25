<template>
	<view class="index">
		<view class="blue-bg"></view>
		<view class="text-title">Home Diet</view>
		<view class="text-subtitle"> 关 心 健 康 更 关 心 你 </view>
		<view class="nutrition" @click="navBefore()">
			<view class="label1">今        日    营    养</view>
			<view class="label2">最个性的营养来自于个性的数据</view>
			<image class='img1' mode="aspectFit" src="../../static/index/food1.jpg"></image>
		</view>
		<view class="recipe"  @click="navItemClick('/pages/index/recommend/recommend')">
			<view class="label1">食    谱    推    荐</view>
			<view class="label2">自然 & 健康</view>
			<image class='img1' mode="aspectFit" src="../../static/index/food2.jpg"></image>
		</view>
		<view class="calorie"  @click="navItemClick('/pages/index/query/query')">
			<view class="label1">热  量  查  询</view>
			<view class="label2">丰富 & 精准</view>
			<image class="img1" mode="aspectFit" src="../../static/index/food3.jpg"></image>
		</view>
		
		<view class="label-sug" @click="navItemClick('/pages/estimate/esitimate')">
			<view class="label1">食  物  估  量</view>
			<view class="label2">便捷 & 安心</view>
			<image class="img1" mode="aspectFit" src="../../static/index/food4.png"></image>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		onLoad() {},
		components: {},
		methods: {
			navItemClick (url) {
				uni.navigateTo({
					url
				})
			},
			async navBefore(){
				const res = await this.$myRequest({
					url: '/user?openid='+getApp().globalData.openid,
				})
				const that=this;
				Promise.all([res]).then(() => {
					if(res.data.data.length==0){
						that.navItemClick("/pages/index/today/today_n")
					}else {
						that.navItemClick("/pages/index/today/today_y")
					}
				})
			}

		}
	}
</script>

<style lang="scss">
	.index {
		background-color: #FFFFFF;
	}
	
	.blue-bg {
		width: 750rpx;
		height: 366rpx;
		background-color: rgba(186, 231, 255, 0.7);
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
		height: 200rpx;
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
		top: 170rpx;

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
			top: 120rpx;
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
		width: 688rpx;
		height: 200rpx;
		background-color: rgb(0, 80, 179);
		border-style: none;
		border-color: unset;
		box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
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
		top: 410rpx;

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
			top: 120rpx;
		}

		.img1 {
			transform: rotate(15deg);
			width: 280rpx;
			height: 226rpx;
			z-index: 2;
			position: absolute;
			left: 430rpx;
			top: -40rpx;
		}
	}

	.calorie {
		width: 688rpx;
		height: 200rpx;
		background-color: rgb(0, 80, 179);
		border-style: none;
		border-color: unset;
		box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
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
		top: 650rpx;

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
			top: 120rpx;
		}

		.img1 {
			transform: rotate(15deg);
			width: 280rpx;
			height: 226rpx;
			z-index: 2;
			position: absolute;
			left: 430rpx;
			top: -40rpx;
		}
	}

	.label-sug {
		width: 688rpx;
		height: 200rpx;
		background-color: rgb(0, 80, 179);
		border-style: none;
		border-color: unset;
		box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
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
		top: 890rpx;
		
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
			top: 120rpx;
		}
		.img1 {
			width: 280rpx;
			height: 226rpx;
			transform: rotate(15deg);
			z-index: 2;
			position: absolute;
			left: 460rpx;
			top: -40rpx;
			
		}
	}
	
</style>
