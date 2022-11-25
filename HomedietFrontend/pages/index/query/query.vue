<template>
	<view>
		<!-- <view class="upper">
			<text>热量查询</text>
		</view> -->
		
		
		<view class="search">
			<image src="../../../static/sousuo.png"></image>
			<uni-search-bar class="searchbar"  @confirm="search"  ></uni-search-bar>
		</view>
	
		<scroll-view class="right" scroll-y>
			<view class="foods" v-for="(item, index) in items" :key="index">
				
				<image mode="aspectFit" :src="item.pic"></image>
				<view class="detail">
				<view class="itemname">{{item.name}}</view>
				<view>蛋白质{{item.protein}}g</view>
				<view>碳水{{item.carb}}g</view>
				<view>脂肪{{item.fat}}g</view>
				<view>热量{{item.calorie}}kcal</view>
				</view>
			</view>
		</scroll-view>

			

	</view>
</template>

<script>
	export default {
		data() {
			return {
				searchvalue : "",
				items: [
				]
			}
		},
		onLoad() {
			this.getMarketDatas()
		},
		components: {},
		methods: {
			async search(e){
				console.log(e)
				const res = await this.$myRequest({
					url: '/foods?name=' + e.value
				})
				console.log(res.data.data)
				Promise.all([res]).then(() => {
					this.items = res.data.data
				})
			},
			async getMarketDatas() {
				const res = await this.$myRequest({
					url: '/foods'
				})
				// console.log(res.data.data)
				Promise.all([res]).then(() => {
					this.items = res.data.data
				})
			}
			}
		}
	
	
</script>

<style lang="scss">
	.upper{
		width: 375px;
		height: 44px;
		color: #FFFFFF;
		background-color: #0050B3 ;
		text-align: left;
		padding-left: 10px;
		font-size: 20px;
		font-weight: 500;
	}
	
	
		
	.search{
		display: flex;
			
		image{
			height: 20px;
			width: 20px;
			position: absolute;
			top: 18px;
			left: 10px;
		}
		
		
		.searchbar{
			width: 350px;
			height: 39px;
			position: absolute;
			top: 0px;
			left: 30px;
			font-size: 14px;
			text-align: left;
			font-family: Arial;
		}	
	}

		
	.right{
		width: 650px;
		height: 89vh;
		position: absolute;
		top: 56px;
	}
	
	.foods {
		//margin: 10rpx 0 10rpx 0;
		width: 52%;
		height: 140px;
		margin-bottom:8px ;
		margin-left: 8px;
		// margin-right: 10px;
		border-radius: 10px;
		// font-weight: bold;
		
		background-color: #FFFFFF;
		display: flex;
		justify-content: left;
		padding-left: 20px;
			
		.itemname{
			font-weight: bold;
		}
			
		image{
			height: 100px;
			width: 100px;
			margin-top: 20px;
		}
		.detail{
			margin-left: 30px;
			margin-top: 20px;
			font-size: 14px;
		
		}
	}

	
		
		

	
	
</style>