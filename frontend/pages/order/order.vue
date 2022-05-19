<template>
	<view>
		<uni-search-bar></uni-search-bar>
		<view class="header">
			<view class="tabbar">
				<view @click="tabClick(index)" v-for="(tabName, index) in tabNames" :key="index" 
				:class="index == activeIndex ? 'activeTab' : ''">
					<text>{{tabName}}</text>
					<view></view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				activeIndex: 0,
				tabNames: [
					"全部",
					"待付款",
					"待提货",
					"已提货"
				]
			}
		},
		methods: {
			tabClick: function(index) {
				// console.log(index)
				this.activeIndex = index
			}
		}
	}
</script>

<style lang="scss">
	uni-search-bar {
		z-index: 1;
		position: fixed;
		top: -5px;
		left: 5px;
		width: 351px;
		border-radius: 15px;
		color: rgba(136, 136, 136, 1);
		text-align: center;
	}

	.header {
		z-index: 0;
		position: fixed;
		left: 0px;
		top: 0px;
		width: 375px;
		height: 95px;
		background-color: rgba(255, 255, 255, 100);
		text-align: center;
		box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.04);

		.tabbar {
			margin-top: 130rpx;
			display: flex;
			
			view {
				width: 42px;
				height: 20px;
				font-size: 14px;
				text-align: center;
				font-family: PingFangSC-regular;
				margin: auto;
				color: rgba(16, 16, 16, 100);
			}
			
			.activeTab {
				color: rgba(0, 80, 179, 100);
				view:nth-child(2) {
					width: 16px;
					height: 3px;
					line-height: 20px;
					border-radius: 22px;
					background-color: rgba(0, 80, 179, 100);
					color: rgba(0, 80, 179, 100);
					font-size: 14px;
					text-align: center;
					font-family: Arial;
					margin: auto;
				}
			}
		}
	}
</style>
