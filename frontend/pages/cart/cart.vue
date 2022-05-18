<template>
	<view>
		<view class="fixbar">
			<checkbox></checkbox>
			<label>全选</label>
			<label>合计:</label>
			<label>￥{{sum}}</label>
			<button>去结算{{}}</button>
		</view>
		<scroll-view class="goods" scroll-y>
			<view class="goodsCard" v-for="(item, index) in items" :key="index">
				<checkbox ></checkbox>
				<image mode="aspectFit" :src="item.img"></image>
				<view>
					<view>{{item.title}}</view>
					<view>
						<text>￥{{item.price}}</text>
					</view>
					<uni-number-box></uni-number-box>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				radio1: 0,
				label1: [{
					text: '全选',
					value: 1
				}],
				sum: 0,
				items: [{
						img: "../../static/market/item1.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: "28.8",
						sold: "2700"
					},
					{
						img: "../../static/market/item2.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: "28.8",
						sold: "2700"
					},
					{
						img: "../../static/market/item3.png",
						title: "语农 散装土鸡蛋  360枚 40斤",
						time: "08月13日",
						price: "28.8",
						sold: "2700"
					}
				]
			}
		},
		methods: {}
	}
</script>

<style lang=scss>
	.fixbar {
		z-index: 1;
		position: fixed;
		top: 990rpx;
		width: 375px;
		height: 60px;
		background-color: rgb(255, 255, 255);
		border-color: rgba(0, 0, 0, 0.04);
		border-width: 1px;
		border-style: solid;

		checkbox {
			margin-top: 30rpx;
			margin-left: 20rpx;
		}

		label:nth-child(2) {
			margin-left: 20rpx;
			font-family: SourceHanSansSC;
			font-weight: 400;
			font-size: 14px;
			color: rgba(144, 144, 144, 1);
			font-style: normal;
			letter-spacing: 0px;
			line-height: 20px;
			text-decoration: none;
		}

		label:nth-child(3) {
			margin-left: 100rpx
		}

		label:nth-child(4) {
			margin-left: 10rpx
		}

		button {
			position: fixed;
			top: 1000rpx;
			left: 500rpx;
		}
	}

	.goods {
		height: 100%;

		.goodsCard {
			display: flex;
			margin: 10rpx 0 10rpx 0;
			height: 300rpx;
			background-color: rgb(255, 255, 255);
			border-style: none;
			border-color: unset;
			box-shadow: rgba(0, 0, 0, 0.12) 0px 2px 6px 0px;
			/* color: rgb(255, 255, 255); */
			border-radius: 8px 12px 12px;
			
			checkbox {
				margin-top: 100rpx;
			}
			
			image {
				overflow: hidden;
				border-color: rgb(187, 187, 187);
				border-width: 0px;
				border-style: solid;
				border-radius: 8rpx;
				box-shadow: none;
				width: 486rpx;
				height: 237rpx;
				margin-left: 10rpx;
				margin-top: 10rpx;
			}

			view {
				view:nth-child(1) {
					font-family: PingFangSC;
					font-weight: 700;
					font-size: 14px;
					color: rgb(16, 16, 16);
					font-style: normal;
					letter-spacing: 0px;
					line-height: 20px;
					text-decoration: none;
					margin-left: 10rpx;
				}


				view:nth-child(2) {
					text:nth-child(1) {
						font-family: PingFangSC;
						font-weight: 700;
						font-size: 16px;
						color: rgba(50, 128, 28, 1);
						font-style: normal;
						letter-spacing: 0px;
						line-height: 22px;
						text-decoration: none;
						margin: 0 10rpx 0 10rpx;
					}
				}
			}
		}
	}
</style>
