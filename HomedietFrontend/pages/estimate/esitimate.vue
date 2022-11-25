<template>
	<view class="main">
		<scroll-view class="left" scroll-y>
			<view v-for="(cate, index) in cates" :class="active==index? 'active' : ''"
				:key="index" @click="clickLeft(index)">
				{{cate}}
			</view>
		</scroll-view>
		<scroll-view class="right" id="right1"  scroll-y>
			<view class="food" v-for="(food, index) in foods" :key="index" >
				<image :src="food.npic"></image>
				<view class="info">{{food.name}} {{food.ngram}}</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
			cates: [
				"碳水类", "蛋白质类", "脂肪类", "蔬菜类"
			],
			types:[
				"谷薯芋、杂豆、主食","蛋类、肉类及制品","坚果、大豆及制品","蔬果和菌藻"
			],
			foods:["../../static/market/item1.png","../../static/c1.png"],
				active: 0,
			}
		},
		methods: {
			
			clickLeft: function(index){
				this.active = index
				this.getMarketDatas(this.types[this.active]);
			},
			async getMarketDatas(type) {
				const that = this
				that.foods=[]
				const res = await this.$myRequest({
					url: '/foods?type='+type
				})
				Promise.all([res]).then(() => {
					res.data.data.forEach(function(element) {
				    if(element.npic.length != 1 &&element.npic.length != 0)
				      {that.foods.push(element)
		               }
           
				});
				console.log(that.foods[2].npic.length)
				})
				
				}
			},
			onLoad: function() {
				this.getMarketDatas("谷薯芋、杂豆、主食")
			}
		}
	
</script>

<style lang=scss>
.main{
	display: flex;
}
.left {
				/* border-right: 10rpx solid #eee; */
				width: 188rpx;
				height: 89vh;
				background-color: #FFFFFF;
				.active {
					background-color: #f5f5f5;
					/* border-radius: 18px; */
					color: rgb(0, 80, 179);
				}
				view {
					width: 190rpx;
					height: 66rpx;
					line-height: 48rpx;
					color: #333;
					text-align: center;
					font-family: OPPOSans;
					font-weight: 400;
					font-size: 28rpx;
					margin: 0rpx 0 0rpx 0rpx;
					padding: 12rpx 0 0 0;
				}
			}
.right{
	width: 532rpx;
	height: 100%;
	
}
.food{
	margin: 10rpx 0 10rpx 0;
	width: 510rpx;
	height: 412rpx;
	background-color: rgb(255, 255, 255);
	border-style: none;
	border-color: unset;
	box-shadow: rgba(0, 0, 0, 0.12) 0px 2px 6px 0px;
	/* color: rgb(255, 255, 255); */
	border-radius: 8px 12px 12px;
	image{
		height: 80%;
		width: 100%;
	}
	.info{
		margin-left: 10px;
	}
	
}
</style>
