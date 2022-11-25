<template>
	<view>
		<view class="upper">
			<view>
				<text>今日碳水循环</text>
				<text>合理搭配，精准减脂</text>
			</view>
		</view>
		<view class="middle">
			<view>
				<image mode="aspectFit" :src="picUrl"></image>
				<text>{{name}}</text>
				<text>星期三</text>
			</view>
			<view>
				<image mode="aspectFit" src="../../../static/index/diamond.jpg"></image>
				<text>三大元素配比</text>
				<image mode="aspectFit" src="../../../static/index/circle.jpg"></image>
			</view>
			<view>
				<image mode="aspectFit" src="../../../static/index/diamond.jpg"></image>
				<text>备注</text>
				<text>低碳水高蛋白</text>
				<button type="default" @click="clickButton()">开始减脂之旅</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name : "Paul",
				picUrl: "/static/mine/user.png"
			}
		},
		onLoad() {
			this.name = getApp().globalData.nickName
			this.picUrl = getApp().globalData.avatarUrl
		},
		components: {},
		methods: {
			clickButton: function(){
				uni.navigateTo({
					url : 'personal_info'
				})
			}
		}
	}
</script>

<style lang="scss">
	
	.upper{
		left: 0px;
		top: 0px;
		width: 375px;
		height: 140px;
		line-height: 20px;
		background-color: rgba(0, 80, 179, 100);
		color: rgba(16, 16, 16, 100);
		font-size: 14px;
		text-align: center;
		font-family: Arial;
		position: absolute;
		
		view{
			text{
				left: 24px;
				top: 26px;
				width: 156px;
				height: 35px;
				color: rgba(255, 255, 255, 100);
				font-size: 26px;
				text-align: left;
				font-family: OPPOSans-bold;
				position: absolute;
			}
			
			text:nth-child(2){
				left: 24px;
				top: 68px;
				width: 126px;
				height: 20px;
				color: rgba(255, 255, 255, 58);
				font-size: 14px;
				text-align: left;
				font-family: OPPOSans-regular;
				position: absolute;
			}
	}
	}
	
	.middle{
        left: 37.5px;
		top: 100px;
		width: 300px;
		height: 460px;
		line-height: 20px;
		border-radius: 30px 30px 30px 30px;
		text-align: center;
		border: 1px solid rgba(187, 187, 187, 100);
        position: absolute;
		background-color: rgba(255, 255, 255, 100);
		
		view{
			
			image{
				left: 34px;
				top: 60px;
				width: 55px;
				height: 55px;
				// border: 2px solid rgba(117, 181, 215, 100);
				position: absolute;
			}
			
			text{
				left: 121px;
				top: 60px;
				width: 42px;
				height: 20px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				text-align: left;
				font-family: OPPOSans-bold;
				position: absolute;
			}
			
			text:nth-of-type(2){
				left: 121px;
				top: 100px;
				width: 42px;
				height: 20px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				text-align: left;
				font-family: OPPOSans-bold;
			}
			
			text:nth-of-type(3){
				left: 199px;
				top: 100px;
				width: 104px;
				height: 20px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				text-align: left;
				font-family: OPPOSans-bold;
			}
		}
			
		view:nth-of-type(2){
			image{
				left: 30px;
				top: 140px;
				width: 26px;
				height: 26px;
				background-color: rgba(255, 255, 255, 100);
				position: absolute;
			}
			
			text{
				left: 64px;
				top: 140px;
				width: 91px;
				height: 22px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				text-align: left;
				font-family: OPPOSans-bold;
			}
			
			image:nth-of-type(2){
				left: 80px;
				top: 160px;
				width: 160px;
				height: 160px;
				background-color: rgba(255, 255, 255, 100);
				position: absolute;
			}
		}
		
		view:nth-child(3){
			image{
				left: 30px;
				top: 340px;
				width: 26px;
				height: 26px;
				background-color: rgba(255, 255, 255, 100);
			}
			
			text{
				left: 64px;
				top: 340px;
				width: 40px;
				height: 26px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				text-align: left;
				font-family: OPPOSans-bold;
			}
			
			text:nth-of-type(2){
				left: 64px;
				top: 360px;
				width: 100px;
				height: 26px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				text-align: left;
				font-family: OPPOSans-bold;
			}
			
			button{
				left: 0px;
				top: 400px;
				width: 217px;
				height: 31px;
				border-radius: 10px 10px 10px 10px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				line-height: 31px;
				text-align: center;
				font-family: Microsoft Yahei;
				border: 1px solid rgba(187, 187, 187, 100);
			}
		}
		
	}
	
	
	
	
</style>