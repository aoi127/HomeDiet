<template>
	<view>
		<view class="upper">
			 <view class="img">
				<image src="../../../static/index/recommend.jpg"></image>
			</view>
			<view class="recipe">
				<view class="title">推荐搭配</view>
				<view>
				<view class="subtitle">早餐</view>
				<view class="breakfast" v-for="(item, index) in bre" :key="index" >
					<view class="pic">
						<image :src="item.food.pic"></image>
					</view>
					<view class="detail">
					<view>{{item.food.name}}  {{item.gram}}g</view>
					<text>蛋白质{{item.food.protein}}g,碳水化合物{{item.food.carb}}g,脂肪{{item.food.fat}}g</text>
					</view>
					<button >加入购物车</button>
				</view>
				</view>
				<view>
				<view class="subtitle">午餐</view>
				<view class="breakfast" v-for="(item, index) in lun" :key="index">
					<view class="pic">
						<image :src="item.food.pic"></image>
					</view>
					<view class="detail">
					<view>{{item.food.name}}  {{item.gram}}g</view>
					<text>蛋白质{{item.food.protein}}g,碳水化合物{{item.food.carb}}g,脂肪{{item.food.fat}}g</text>
					</view>
					<button >加入购物车</button>
				</view>
				</view>
				<view>
				<view class="subtitle">晚餐</view>
				<view class="breakfast" v-for="(item, index) in lun" :key="index">
					<view class="pic">
					<image :src="item.food.pic"></image>
					</view>
					<view class="detail">
					<view>{{item.food.name}}  {{item.gram}}g</view>
					<text>蛋白质{{item.food.protein}}g,碳水化合物{{item.food.carb}}g,脂肪{{item.food.fat}}g</text>
					</view>
					<button >加入购物车</button>
				</view>
				</view>
				
				
			</view>

		</view>
		<view class="lower">
			<view class="title">今日摄取统计</view>
			<view>总热量{{cal}}kcal</view>
			<view>碳水化合物{{cho}}g</view>
			<view>蛋白质{{pro}}g</view>
			<view>脂肪{{fat}}g</view>
		</view>	
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				name : "牛奶",
				bre:[],
				lun:[],
				din:[],
				pro:0,
				cho:0,
				fat:0,
				cal:0
			}
		},
		onLoad() {
			this.getDiet()
		},
		components: {},
		methods: {
			async getDiet(){
				const that = this;
				const res = await this.$myRequest({
					url: '/diet?openid='+getApp().globalData.openid
				})
				
				Promise.all([res]).then(() => {
					that.items = res.data.data.list
					that.cho = res.data.data.cho
					that.pro = res.data.data.pro
					that.fat = res.data.data.fat
					that.cal = res.data.data.cal
					that.bre = res.data.data.list.slice(0,4)
					that.lun = res.data.data.list.slice(4,8)
					that.din = res.data.data.list.slice(8,12)
					
				})
			}
			
		}
	}
	
</script>

<style lang="scss">
	.upper{
		width: 100%;
		.img{
			width: 100%;
			height: 230px;
			image{
				width: 100%;
				height: 100%;
			}
		}
		.recipe{
			
			width: 308px;
			background-color: #F7F7F7;
			border: 1px solid rgba(187, 187, 187, 100);
			border-radius: 15px;
			position: absolute;
			padding-bottom: 20px;
			top: 80px;
			left: 40px;
			.title{
				padding-left: 10px;
				padding-top: 10px;
				color: rgba(16, 16, 16, 100);
				font-size: 18px;
				font-family: PingFangSC-regular;
			}
			.subtitle{
				padding-left: 15px;
				padding-top: 5px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				font-family: PingFangSC-regular;
			}
			.breakfast{
				display: flex;
				width: 274px;
				height: 61px;
				border: 1px solid rgba(187, 187, 187, 100);
				margin-left: 15px;
				margin-top: 15px;
				 padding-left: 5px;
				// justify-content: center;
				align-items: center;
				position: relative;
				.pic{
					width: 50px;
					height: 35px;
					margin-right: 10px;
					image{
						width: 100%;
						height: 100%;
					}
				}
				.detail{
					font-size: 14px;
					text{
						font-size: 12px;
					}
				}
				button{
					width: 170rpx;
					height: 44rpx;
					font-size: 11px;
					border-radius: 20px;
					line-height: 44rpx;
					color: #FFFFFF;
					background-color:rgb(0, 80, 179) ;
					position: absolute;
					top: 8px;
					right: 10px;
				}
			}
		}
	}
	

		

		
	
	
	.lower{
		left: 41px;
		top: 1180px;
		width: 308px;
		height: 238px;
		border-radius: 20px 20px 20px 20px;
		color: rgba(16, 16, 16, 100);
		font-size: 14px;
		font-family: Arial;
		border: 1px solid rgba(187, 187, 187, 100);
		position: absolute;
		.title{
			padding-left: 10px;
			padding-top: 10px;
			color: rgba(16, 16, 16, 100);
			font-size: 18px;
			font-family: PingFangSC-regular;
		}


	}
	
	
</style>