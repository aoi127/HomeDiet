<template>
	<view>
		<view class="upper">
			<text>热量查询</text>
		</view>
		
		<view class="middle">
			<uni-search-bar class="searchbar" @confirm="search" @input="input"></uni-search-bar>
			<scroll-view class="results" scroll-y>
				
				
				<view class="goodsCard" v-for="(item, index) in items" :key="index" v-if="item.type == active">
					<image mode="aspectFit" :src="item.img" @click="clickItem()"></image>
					<view>{{item.title}}</view>
					<view>
						<text>蛋白质{{}}</text>
						<text>脂肪{{}}</text>
						<text>热量{{}}</text>
					</view>
					<uni-number-box @change="goodsNumChange($event, index)"></uni-number-box>
				</view>
				
			   
			</scroll-view>
		</view>
	</view>
</template>

<script>
</script>

<style lang="scss">
	.upper{
		left: 0px;
		top: 0px;
		width: 375px;
		height: 44px;
		background-color: rgba(149, 222, 100, 100);
		text-align: center;
		position: absolute;
		
		text{
			left: 20px;
			top: 10px;
			width: 72px;
			height: 25px;
			color: rgba(51, 51, 51, 100);
			font-size: 18px;
			text-align: left;
			font-family: PingFangSC-bold;
			position: absolute;
		}
	}
	
	.middle{
		.searchbar{
			left: 0px;
			top: 40px;
			width: 360px;
			height: 39px;
			background-color: rgba(255, 0, 0, 0);
			color: rgba(136, 136, 136, 100);
			font-size: 14px;
			text-align: left;
			font-family: Arial;
		}
	}
	
	
</style>