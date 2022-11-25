<template>
	<view>
		
		<view class="upper">
			<image mode="aspectFit"  :src="picUrl"></image>
			<text>下午好，{{name}}</text>
			<text @click="clickText()"> 个人信息 ></text>
		</view>
		<view>
		<scroll-view class="middle" scroll-y>
			<view class="first">
				<text>体重趋势</text>
				<image mode="aspectFit" src="../../../static/index/line.jpg"></image>
			</view>
			
			 <view class="second">
				 <view class="second_in">
				 	<text>基础代谢率</text>
				 	<text>{{dat1}}kcal</text>
				 </view>
				 <view class="second_in">
				<text>每日消耗热量</text>
				<text>{{dat2}}kcal</text>
				 </view>
				 <view class="second_in">
				<text>减脂每日摄入</text>
				<text>{{dat3}}kcal</text>
				 </view>


			</view> 
		
			
			<view class="third">
				<view class="title">
				<view>三大元素配比</view>
				<text>———————————————————</text>
				</view>
				<view class="second_in">
					<text>蛋白质</text>
					<text>{{dat4}}g</text>
				</view>
				<view class="second_in">
					<text>碳水化合物</text>
					<text>{{dat5}}g</text>
				</view>
				<view class="second_in">
					<text>脂肪</text>
					<text>{{dat6}}g</text>
				</view>
			</view>
			
		</scroll-view>
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name:"Paul",
				picUrl:"../../static/mine/user.jpg",
				sex: 0,
				sex_string: "",
				weight: 0,
				height: 0,
				age: 0,
				bfr: 0,
				tdee: 0,
		
				

				dat1 : 100,
				dat2 : 0,
				dat3 : 0,
				dat4 : 100,
				dat5 : 0,
				dat6 : 0,
				id:0
			}
		},
		onLoad(options) {
			this.name=getApp().globalData.nickName
			this.picUrl=getApp().globalData.avatarUrl
			this.getUser()
			
			


		},
		
		components: {},
		methods: {
			clickText: function(){
				uni.navigateTo({
					url : 'personal_info?exist=1&id='+this.id
				})
			},
			async getUser(){
				const res = await this.$myRequest({
					url: '/user?openid=' + getApp().globalData.openid
				})
				 const that = this
				Promise.all([res]).then(() => {
				 that.dat1 = res.data.data[0].bmr
				 that.dat2 = res.data.data[0].tdee
				 that.dat3 = res.data.data[0].cal
				 that.dat4 = res.data.data[0].pro
				 that.dat5 = res.data.data[0].cho
				 that.dat6 = res.data.data[0].fat
				 that.id = res.data.data[0].id
				})
			}
		},
	}
</script>

<style lang="scss">
	.upper{

		width: 375px;
		height: 40px;
		line-height: 20px;
		//background-color: rgba(186, 231, 255, 0.7);
		color: rgba(16, 16, 16, 100);
		font-size: 14px;
		text-align: center;
		font-family: Arial;
		image{
			left: 45px;
			top: 20px;
			width: 60px;
			height: 60px;
			position: absolute;
		}
		
		text{
			left: 23px;
			top: 10px;
			//width: 42px;
			height: 20px;
			color: rgba(16,16,16, 100);
			font-weight: bold;
			font-size: 16px;
			text-align: left;
			font-family: PingFangSC-regular;
			position: absolute;
		}
		
		text:nth-of-type(2){
			left: 280px;
			top: 10px;
			width: 80px;
			height: 20px;
			color: 	#808080;
			font-size: 14px;
			text-align: left;
			font-family: PingFangSC-regular;
			position: absolute;

		}
		
		text:nth-of-type(3){
			left: 300px;
			top: 42px;
			width: 80px;
			height: 20px;
			color: rgba(255, 255, 255, 100);
			font-size: 14px;
			text-align: left;
			font-family: PingFangSC-regular;
			position: absolute;
		}
	}
	
	.blue-bg {
		width: 750rpx;
		height: 366rpx;
		background-color: rgba(186, 231, 255, 0.7);
	}
	
	.middle{
		height:650px;
		.first{
			background-color: rgba(255, 255, 255, 100);
			height: 203px;
			width: 328px;
			border-radius: 15px;
			border: 1px solid #BBBBBB;
			box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
			padding-top: 15px;
			padding-bottom: 0px;
			font-size: 14px;
            margin-top: 5px;
			margin-left: 23px;
			text{
				margin-left: 30px;
			}
			image{
				width: 550rpx;
				height: 350rpx;
				margin-left: 20px;
			}
		}
		
		.second{
			background-color: rgba(255, 255, 255, 100);
			height: 90px;
			width: 328px;
			border-radius: 15px;
			border: 1px solid #BBBBBB;
			box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
			padding-top: 10px;
			margin-left: 23px;
			font-size: 14px;
			margin-top: 20px;
		}
		.second_in{
			display: flex;
			justify-content: space-between;
			width: 80%;
			margin: auto;
			margin-top: 5px;
			
		}
		
		.third{
            background-color: rgba(255, 255, 255, 100);
			height: 150px;
			width: 328px;
			border-radius: 15px;
			border: 1px solid #BBBBBB;
			box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
			font-size: 14px;
			margin-top: 20px;
			margin-left: 23px;
			padding-bottom: 0;
		}
		.title{
			margin-left: 30px;
			margin-top: 15px;
		}
	
	}
	
</style>