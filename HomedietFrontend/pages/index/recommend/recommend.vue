<template>
	<view>
		<view class="upper">
			<view class="img">
				<image src="../../../static/index/recommend.jpg"></image>
			</view>
			<view class="recipe">
				<uni-fav :checked="star" class="favBtn" @click="favClick()" />
				<uni-data-select v-model="value" class="select-bar" :localdata="dietTime" @change="selectedChange"
					:clear="true">
				</uni-data-select>
				<view class="title">推荐搭配</view>
				<view>
					<view class="subtitle">早餐</view>
					<view class="breakfast" v-for="(item, index) in bre" :key="index">
						<view class="pic">
							<image :src="item.food.pic"></image>
						</view>
						<view class="detail">
							<view>{{item.food.name}} {{item.gram}}g</view>
							<text>蛋白质{{item.food.protein}}g,碳水化合物{{item.food.carb}}g,脂肪{{item.food.fat}}g</text>
						</view>
						<button @click="buttonClick(item.food.itemid)">加入购物车</button>
					</view>
				</view>
				<view>
					<view class="subtitle">午餐</view>
					<view class="breakfast" v-for="(item, index) in lun" :key="index">
						<view class="pic">
							<image :src="item.food.pic"></image>
						</view>
						<view class="detail">
							<view>{{item.food.name}} {{item.gram}}g</view>
							<text>蛋白质{{item.food.protein}}g,碳水化合物{{item.food.carb}}g,脂肪{{item.food.fat}}g</text>
						</view>
						<button @click="buttonClick(item.food.itemid)">加入购物车</button>
					</view>
				</view>
				<view>
					<view class="subtitle">晚餐</view>
					<view class="breakfast" v-for="(item, index) in lun" :key="index">
						<view class="pic">
							<image :src="item.food.pic"></image>
						</view>
						<view class="detail">
							<view>{{item.food.name}} {{item.gram}}g</view>
							<text>蛋白质{{item.food.protein}}g,碳水化合物{{item.food.carb}}g,脂肪{{item.food.fat}}g</text>
						</view>
						<button @click="buttonClick(item.food.itemid)">加入购物车</button>
					</view>
				</view>


			</view>

		</view>
		<view class="lower">
			<view class="title" style="border-bottom: 1px solid;">今日摄取统计</view>
			<view class="info">
				<view>🔥 总热量:{{cal}}kcal</view>
				<view>🍚 碳水化合物:{{cho}}g</view>
				<view>🥚 蛋白质:{{pro}}g</view>
				<view>🍫 脂肪:{{fat}}g</view>
			</view>
		</view>
	</view>

</template>

<script>
	import {
		dietTime
	} from '../../../data/1777/diets';
	// import data from "@/data/1777/diets.js"
	export default {
		data() {
			return {
				name: "牛奶",
				bre: [],
				lun: [],
				din: [],
				pro: 0,
				cho: 0,
				fat: 0,
				cal: 0,
				star: false,
				selIndex: 0,
				dietTime: [{
					value: 0,
					text: '回到今天'
				}],
				todayDiet: {},
				history: {}
			}
		},
		onLoad() {
			this.geTodayDiet(),
				this.getHistory()
		},
		components: {},
		methods: {
			async geTodayDiet() {
				const that = this;
				const res = await this.$myRequest({
					url: '/diet?openid=' + getApp().globalData.openid
				})

				// console.log(res.data.data)

				Promise.all([res]).then(() => {
					that.todayDiet = res.data.data
					that.items = that.todayDiet.list
					that.cho = that.todayDiet.cho
					that.pro = that.todayDiet.pro
					that.fat = that.todayDiet.fat
					that.cal = that.todayDiet.cal
					that.bre.length = 0
					that.lun.length = 0
					that.din.length = 0
					for (let i = 0; i < that.todayDiet.list.length; i++) {
						if (that.todayDiet.list[i].time == "早餐") {
							that.bre.push(that.todayDiet.list[i])
						} else if (that.todayDiet.list[i].time == "午餐") {
							that.lun.push(that.todayDiet.list[i])
						} else {
							that.din.push(that.todayDiet.list[i])
						}
					}
				})
			},
			async getHistory() {
				const that = this;
				const res = await this.$myRequest({
					url: '/collect/get?openid=' + getApp().globalData.openid
				})
				// console.log("getHistory:")
				Promise.all([res]).then(() => {
					that.history = res.data.data
					for (let i = 0; i < that.history.length; i++) {
						// console.log(that.history)
						that.dietTime.push({
							value: i + 1,
							text: that.history[i].time
						})
					}
				})

				// console.log("getHistory end")
			},
			async buttonClick(id) {
				console.log("点击按钮")
				// console.log("右侧按钮信息：", e)
				const res = await this.$myRequest({
					url: '/cart/add',
					data: {
						itemid: id + 1,
						openid: getApp().globalData.openid
					},
					method: "POST"
				})
				// console.log("商品数量：", this.info.num)
				// console.log("加入购物车的商品信息", res)
				if (res.data.msg == "成功") {
					uni.showToast({
						title: `已加入购物车`,
						icon: 'none'
					})
				}
			},
			geToday() {
				let date = new Date();
				let year = date.getFullYear();
				// 在日期格式中，月份是从0开始的，因此要加0，使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
				let month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
				let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
				let today = year + '.' + month + '.' + day
				return today
			},
			async favClick() {
				if (this.star) { // 取消收藏
					for (let i = 0; i < this.todayDiet.list.length; i++) {
						// console.log(this.todayDiet.list[i].id)
						const res = await this.$myRequest({
							url: '/collect/remove?dietid=' + this.todayDiet.list[i].id + '&openid=' + getApp()
								.globalData.openid,
							// data: {
							// 	dietid: this.todayDiet.list[i].id,
							// 	openid: getApp().globalData.openid,
							// },
							method: "DELETE"
						})
					}
				} else { // 收藏
					for (let i = 0; i < this.todayDiet.list.length; i++) {
						// console.log(this.todayDiet.list[i].id)
						const res = await this.$myRequest({
							url: '/collect/add?dietid=' + this.todayDiet.list[i].id + '&openid=' + getApp()
								.globalData.openid,
							data: {
								dietid: this.todayDiet.list[i].id,
								openid: getApp().globalData.openid,
							},
							method: "POST"
						})
					}
				}
				this.star = !this.star
			},
			async selectedChange(e) {
				// console.log(this.history[0])
				if (e == 0) {
					console.log('e==0')
					this.geTodayDiet()
				} else {
					const that = this;
					const res = await this.$myRequest({
						url: '/collect/get?openid=' + getApp().globalData.openid
					})
					Promise.all([res]).then(() => {
						that.history = res.data.data
						var i = 0
						for (; i < that.history.length; i++) {
							if (that.history[i].time == that.dietTime[e].text) {
								break
							}
						}
						that.items = that.history[i].list
						that.bre.length = 0
						that.lun.length = 0
						that.din.length = 0
						for (let j = 0; j < that.history[i].list.length; j++) {
							if (that.history[i].list[j].time == "早餐") {
								that.bre.push(that.history[i].list[j])
							} else if (that.history[i].list[j].time == "午餐") {
								that.lun.push(that.history[i].list[j])
							} else {
								that.din.push(that.history[i].list[j])
							}
						}
					})
				}
			}
		}
	}
</script>

<style lang="scss">
	.upper {
		width: 100%;

		.img {
			width: 100%;
			height: 230px;

			image {
				width: 100%;
				height: 100%;
			}
		}

		.recipe {
			width: 308px;
			background-color: #F7F7F7;
			border: 1px solid rgba(187, 187, 187, 100);
			box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
			border-radius: 15px;
			position: absolute;
			padding-bottom: 20px;
			top: 80px;
			left: 40px;

			// margin-bottom: 1px;
			.favBtn {
				position: absolute;
				left: 90px;
				top: 15px;
			}

			.select-bar {
				position: absolute;
				width: 120px;
				left: 174px;
				top: 10px;
			}

			.title {
				padding-left: 10px;
				padding-top: 10px;
				color: rgba(16, 16, 16, 100);
				font-size: 18px;
				font-family: PingFangSC-regular;
			}

			.subtitle {
				padding-left: 15px;
				padding-top: 5px;
				color: rgba(16, 16, 16, 100);
				font-size: 14px;
				font-family: PingFangSC-regular;
			}

			.breakfast {
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

				.pic {
					width: 50px;
					height: 35px;
					margin-right: 10px;

					image {
						width: 100%;
						height: 100%;
					}
				}

				.detail {
					font-size: 14px;

					text {
						font-size: 12px;
					}
				}

				button {
					width: 170rpx;
					height: 44rpx;
					font-size: 11px;
					border-radius: 20px;
					line-height: 44rpx;
					color: #FFFFFF;
					background-color: rgb(0, 80, 179);
					position: absolute;
					top: 8px;
					right: 10px;
				}
			}
		}
	}


	.lower {
		left: 41px;
		top: 1180px;
		width: 308px;
		height: 180px;
		border-radius: 20px 20px 20px 20px;
		color: rgba(16, 16, 16, 100);
		font-size: 14px;
		font-family: Arial;
		border: 1px solid rgba(187, 187, 187, 100);
		box-shadow: rgb(64, 169, 255) 0rpx 4rpx 12rpx 0rpx;
		position: absolute;
		margin-bottom: 1px;

		.title {
			padding-left: 10px;
			padding-top: 10px;
			color: rgba(16, 16, 16, 100);
			font-size: 18px;
			margin-bottom: 10px;
			font-family: PingFangSC-regular;
			text-align: center;
		}

		.info {
			margin-left: 90px;
			line-height: 30px;
			width: 200px;
		}


	}
</style>
