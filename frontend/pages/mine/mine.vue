<template>
	<view>
		<view class="top">
			<image></image>
			<label>146****8006</label>
			<label>账号管理(->)</label>
		</view>
		<view class="nav">
			<view class="nav_item" v-for="(item, index) in navs" :key="index" @click="navItemClick(item.path)">
				<view :class="item.icon"></view>
				<text>{{item.title}}</text>
			</view>
		</view>
		<view>个人信息/Personal Imformation</view>
		<view>在线服务/Online Service</view>
		<view>帮助与反馈/Help and Feedback</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				navs: [{
						// icon :'iconfont icon-ziyuan',
						title: '全部订单',
						// path: '/pages/goods/goods'
					},
					{
						// icon :'iconfont icon-guanyuwomen',
						title: '待付款',
						// path: '/pages/contact/contact'
					},
					{
						// icon :'iconfont icon-tupian',
						title: '待提货',
						// path: '/pages/pics/pics'
					},
					{
						// icon :'iconfont icon-shipin',
						title: '已提货',
						// path: '/pages/videos/videos'
					}
				]
			}
		},
		methods: {

		}
	}
</script>

<style lang="scss">
	.top {
		display: flex;
		image {
			width:10rpx;
			height:10rpx;
		}
	}
	.nav {
		display: flex;

		.nav_item {
			width: 25%;
			text-align: center;

			view {
				width: 120rpx;
				height: 120rpx;
				background: $shop-color;
				border-radius: 60rpx;
				margin: 10px auto;
				line-height: 120rpx;
				color: #fff;
				font-size: 50rpx;
			}


			text {
				font-size: 30rpx;
			}
		}
	}
</style>
