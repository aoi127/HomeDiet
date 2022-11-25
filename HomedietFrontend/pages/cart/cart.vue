<template>
	<view>
		<view class="fixbar">
			<checkbox-group @change="checkAll">
				<checkbox :checked="checkedAll"></checkbox>
			</checkbox-group>
			<label>全选</label>
			<label @click="deleteItems()">删除</label>
			<label>合计:</label>
			<label>￥{{sum}}</label>
			<button @click="createOrder()">去结算</button>
		</view>
		<scroll-view class="goods" scroll-y>
			<view class="goodsCard" v-for="(card, index) in cards" :key="index">
				<checkbox-group @change="select(index)">
					<checkbox :checked="card.selected"></checkbox>
				</checkbox-group>
				<image :src="card.item.pic" ></image>
				<view>
					<view>{{card.item.name}}</view>
					<view>
						<text>￥{{card.cost}}</text>
						<!-- <uni-number-box></uni-number-box> -->
						<uni-number-box   min=1 :value="card.num" @change="goodsNumChange($event, index)" background="#fff" color="#000"></uni-number-box>
					</view>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				checkedAll: false,
				sum: 0,
				cards: []
			}
		},
		methods: {
			deleteItems() {
				for(let i = 0; i < this.cards.length; i++) {
					if(this.cards[i].selected) {
						for(let j = 0; j < this.cards[i].num; j++) {
							this.$myRequest({
								url: '/cart/sub',
								data: {
									itemid : this.cards[i].item.id,
									openid: getApp().globalData.openid
								},
								method : "POST"
							})	
						}
					}
				}
				this.checkedAll = false
				this.getCartDatas()
			},
			async createOrder(index) {
				var tmporders = []
				for(let i = 0; i < this.cards.length; i++) {
					var tmporder = {
						itemid: this.cards[i].item.id,
						num: this.cards[i].num,
						openid: getApp().globalData.openid
					}
					if(this.cards[i].selected) tmporders.push(tmporder)
					// console.log("this.cards[i].selected:",this.cards[i].selected)
				}
				
				console.log("tmporders:", tmporders)
				
				const res = await this.$myRequest({
					url: '/myorder/add',
					data: {
						myOrders: tmporders,
						openid: getApp().globalData.openid
					},
					method : "POST"
				})
				
				Promise.all([res]).then(() => {
					uni.showToast({
						title: `下单成功`,
						icon: 'none'
					})
				})
			},
			async getCartDatas() {
				const res = await this.$myRequest({
					url: '/cart?openid=' + getApp().globalData.openid
				})
				// console.log(res)
				this.cards = res.data.data
				for(var i = 0; i < this.cards.length; i++) {
					this.$set(this.cards[i], 'selected', false)
					this.$set(this.cards[i], 'cost', this.cards[i].item.price * this.cards[i].num)
				}
				this.sum = 0
				// console.log(this.cards)
			},
			async goodsNumChange(e, index) {
				if(e > this.cards[index].num) {
					var res;
					for(var i = 0; i < e - this.cards[index].num; i++) {
						res = await this.$myRequest({
							url: '/cart/add',
							data: {
								itemid: this.cards[index].item.id,
								openid : getApp().globalData.openid
							},
							method : "POST"
						})	
					}
				}
				else {
					var res;
					for(var i = 0; i < this.cards[index].num - e; i++) {
						res = await this.$myRequest({
							url: '/cart/sub',
							data: {
								itemid: this.cards[index].item.id,
								openid : getApp().globalData.openid
							},
							method : "POST"
						})
					}
				}
				// console.log("待添加",this.cards[index])
				this.cards[index].num = e
				if(this.cards[index].selected)
					this.sum = this.sum - this.cards[index].cost
				this.cards[index].cost = this.cards[index].item.price * e;
				if(this.cards[index].selected)
					this.sum = this.sum + this.cards[index].cost
				
			},
			select: function(index) {
				console.log("index", index)
				if(this.cards[index].selected) { // 取消勾选
					console.log("取消勾选")
					this.sum = this.sum - this.cards[index].cost;
					this.cards[index].selected = false
				}
				else { // 勾选
				console.log("勾选")
					this.sum = this.sum + this.cards[index].cost;
					this.cards[index].selected = true
				}
			},
			checkAll: function(e) {
				// console.log("进入checkAll函数")
				if(this.checkedAll) {
					this.checkedAll = false
					this.sum = 0;
					for(var i = 0; i < this.cards.length; i++) {
						this.$set(this.cards[i], 'selected', false)
					}
					console.log("取消全选, checkedAll的值为", this.checkedAll);
				}
				else {
					this.checkedAll = true
					this.sum = 0;
					for(var i = 0; i < this.cards.length; i++) {
						this.$set(this.cards[i], 'selected', true)
						this.sum = this.sum + this.cards[i].cost;
					}
					console.log("全选, checkedAll的值为", this.checkedAll);
				}
			}
		},
		onLoad: function() {
			this.getCartDatas()
		},
		onTabItemTap: function() {
			this.getCartDatas()
			console.log(getApp().globalData.openid)
		}
	}
</script>

<style lang=scss>
	.fixbar {
		display: flex;
		z-index: 1;
		position: fixed;
		top: 990rpx;
		width: 375px;
		height: 60px;
		background-color: rgb(255, 255, 255);
		border-color: rgba(0, 0, 0, 0.04);
		border-width: 1px;
		border-style: solid;

		checkbox-group {
			margin-top: 30rpx;
			margin-left: 20rpx;
		}

		label:nth-child(2) {
			margin-top: 35rpx;
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
			margin-top: 35rpx;
			margin-left: 40rpx;
			font-family: SourceHanSansSC;
			font-weight: 400;
			font-size: 14px;
			color: rgba(255, 0, 0, 0.7);
			font-style: normal;
			letter-spacing: 0px;
			line-height: 20px;
			text-decoration: none;
		}
		label:nth-child(4) {
			margin-top: 35rpx;
			margin-left: 40rpx;
			width: 42px;
			height: 20px;
			color: rgba(16, 16, 16, 64);
			font-size: 14px;
			text-align: left;
			font-family: SourceHanSansSC-bold;
		}

		label:nth-child(5) {
			margin-top: 32rpx;
			margin-left: 0rpx;
			width: auto;
			height: 22px;
			color: rgba(0, 80, 179, 100);
			font-size: 16px;
			text-align: left;
			font-family: PingFangSC-bold;
		}

		button {
			position: fixed;
			top: 1004rpx;
			left: 520rpx;
			width: 110px;
			height: 40px;
			line-height: 40px;
			border-radius: 100px;
			background-color: rgba(0, 80, 179, 1);
			color: rgba(255, 255, 255, 100);
			font-size: 14px;
			text-align: center;
			font-family: Arial;
			border: 1px solid rgba(0, 80, 179, 100);
		}
	}

	.goods {
		margin-bottom: 10rpx;
		.goodsCard {
			display: flex;
			position: relative;
			margin: 10rpx 0 10rpx 0;
			height: 256rpx;
			background-color: rgb(255, 255, 255);
			border-style: none;
			border-color: unset;
			box-shadow: rgba(0, 0, 0, 0.12) 0px 2px 6px 0px;
			/* color: rgb(255, 255, 255); */
			border-radius: 8px 12px 12px;
			
			checkbox-group {
				margin-top: 100rpx;
				margin-left: 22rpx;
			}
			
			image {
				display: block;
				margin-left:0px;
				margin-right: 8px;
				margin-top: 22px;
				width: 100px;
				height: 100px;
				border-radius: 14px;
				vertical-align: middle;
			}

			view {
				margin-top: 5px;
				width:90%;
				/* height: 80%; */
				/* 商品简介 */
				view:nth-child(1) {
					height: 40%;
					font-family: PingFangSC;
					font-weight: 700;
					font-size: 14px;
					color: rgb(16, 16, 16);
					font-style: normal;
					letter-spacing: 0px;
					line-height: 20px;
					text-decoration: none;
					margin-left: 10rpx;  
					/* padding-bottom: 3px; */
				}

				/* 商品价格  numberbox*/
				view:nth-child(2) {
					position: relative;
					display: flex;
					height: 50%;
					
					
					text:nth-child(1) {
						font-family: PingFangSC;
						font-weight: 700;
						font-size: 16px;
						
						font-style: normal;
						letter-spacing: 0px;
						line-height: 22px;
						text-decoration: none;
/* 						margin: 0 10rpx 0 10rpx;
						margin-top: -13rpx; */
						
						position: absolute;
						bottom: 5px;
						color: rgb(0, 80, 179);
					/* 	justify-content: space-between;
						align-items: center; */
						
					}
					 uni-number-box{
						position: absolute;
						bottom: 5px;
						right: 8px;
						margin-top: 0;
						width: 50%;
						/* background: #ffffff; */
						
						
						/* uni-numbox__plus uni-numbox-btns{
							margin-top: 0;
						} */
					} 
				}
			}
		}
	}
</style>
