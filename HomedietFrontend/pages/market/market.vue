<template>
	<view class="market">
		<uni-search-bar @confirm="search"></uni-search-bar>
		<view class="main">
			<scroll-view class="left" scroll-y>
				<view v-for="(cate, index) in cates" :class="active==index? 'active' : ''"
					:key="index" @click="clickLeft(index)">
					{{cate}}
				</view>
			</scroll-view>
			<scroll-view class="right" scroll-y>
				<view class="goodsCard" v-for="(item, index) in items" :key="index" v-if="item.type == cates[active] || active == 0">
					<image :src="item.pic" @click="clickItem(item.id)"></image>
					<view>{{item.name}}</view>
					<view style="overflow: hidden; height: 20px;">{{item.des}}</view>
					<view>
						<text>￥{{item.price}}</text>
						<text></text>
					</view>
					<button type="primary" @click="buttonClick(item.id)">+</button>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				cates: [
					"为你推荐", "水果区", "蔬菜区", "家禽区", "水产区", "肉食区",
					"蛋类区", "豆制品", "调味品", "米面粮油", "餐食烘焙", "营养早餐",
					"鲜奶制品", "休闲食品", "酒水饮料"
				],
				active: 0,
				items: [
				]
			}
		},
		methods: {
			async search(e){
				console.log(e)
				const res = await this.$myRequest({
					url: '/item?name=' + e.value
				})
				// console.log(res.data.data)
				Promise.all([res]).then(() => {
					this.items = res.data.data
				})
			},
			clickLeft: function(index){
				this.active = index
			},
			clickItem: function(id){
				uni.navigateTo({
					url : '../../pages/goods-detail/goods-detail?id=' + id
				})
			},
			async buttonClick(id) {
				console.log("点击按钮")
				// console.log("右侧按钮信息：", e)
				const res = await this.$myRequest({
					url: '/cart/add',
					data: {
						itemid: id,
						openid : getApp().globalData.openid
					},
					method : "POST"
				})
				// console.log("商品数量：", this.info.num)
				// console.log("加入购物车的商品信息", res)
				if(res.data.msg == "成功")
				{
					uni.showToast({
						title: `已加入购物车`,
						icon: 'none'
					})
				}
			},
			async getMarketDatas() {
				const res = await this.$myRequest({
					url: '/item'
				})
				// console.log(res.data.data)
				Promise.all([res]).then(() => {
					this.items = res.data.data
					for (let i = 0; i < this.items.length; i++) {
						if (this.items[i].des == 'nan') {
							this.items[i].des = ''
						}
						console.log(this.items[i].des)
					}
				})
			}
		},
		onLoad: function() {
			this.getMarketDatas()
		},
		onTabItemTap: function() {
			this.getMarketDatas()
		}
	}
</script>

<style lang=scss>

	.market {
		.main {
			display: flex;
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

			.right {
				width: 100%;
				height: 89vh;
				padding: 0;
				.goodsCard {
					margin: 10rpx 0px 0rpx 3px;
					width: 96%;
					height: 430rpx;
					background-color: rgb(255, 255, 255);
					border-style: none;
					border-color: unset;
					box-shadow: rgba(0, 0, 0, 0.12) 0px 2px 6px 0px;
					
					border-radius: 8px 12px 12px;
					padding-bottom: 10px;

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

					view:nth-child(2) {
						font-family: PingFangSC;
						font-weight: 700;
						font-size: 14px;
						color: rgb(16, 16, 16);
						font-style: normal;
						letter-spacing: 0px;
						line-height: 20px;
						text-decoration: none;
						margin-left: 10rpx;
						
						overflow: hidden;
						word-break: break-all;  
						text-overflow: ellipsis;  /* 超出部分省略号 */
						display: -webkit-box; /** 对象作为伸缩盒子模型显示 **/
						-webkit-box-orient: vertical; /** 设置或检索伸缩盒对象的子元素的排列方式 **/
						-webkit-line-clamp: 2; /** 显示的行数 **/
					}

					view:nth-child(3) {
						font-family: PingFangSC;
						font-weight: 400;
						font-size: 13px;
						color: rgb(16, 16, 16);
						font-style: normal;
						letter-spacing: 0px;
						line-height: 18px;
						text-decoration: none;
						margin: 10rpx;
					}

					view:nth-child(4) {
						text:nth-child(1) {
							font-family: PingFangSC;
							font-weight: 700;
							font-size: 16px;
							color: rgb(0, 80, 179);
							font-style: normal;
							letter-spacing: 0px;
							line-height: 22px;
							text-decoration: none;
							margin: 0 10rpx 0 10rpx;
						}

						text:nth-child(2) {
							font-family: PingFangSC;
							font-weight: 400;
							font-size: 14px;
							color: rgba(0, 0, 0, 0.54);
							font-style: normal;
							letter-spacing: 0px;
							line-height: 20px;
							text-decoration: none;
						}

					}

					button {
						top: -55rpx;
						left: 200rpx;
						width: 55rpx;
						height: 55rpx;
						background-color: rgb(0, 80, 179);
						/* border-style: none;
						border-color: unset; */
						color: rgb(250, 250, 250);
						border-radius: 100%;
						font-size: 50rpx;
						text-align: center;
						font-weight: normal;
						font-style: normal;
						white-space: pre;
						justify-content: center;
						display: flex;
						align-items: center;
					}
				}
			}
		}
	}
</style>
