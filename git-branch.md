# git 慣例規則

## 分支
1. master - 主要分支, 最新主要的版本
2. release - 釋出到用戶的相關分支, 釋出的版本
3. dev/developer/feature - 各版本的新增功能的分支
4. test - 開發/測試分支

## 合併規則
1. dev/developer/feature 開發到段落後，會合併到 test，去測試環境上更新伺服器的版本，待QA(測試人員/工程師)進行測試
2. 1.步驟的測試通過的話，會把 dev/developer/feature 合併到 master/release，等待IT/DEVOPS/SRE/有權限的人員去更新正式環境的伺服器

官網/客服/財務系統/其他系統
// TX-12 - 被指派要處理的需求/bug的單子
dev-official/groupA/krace-TX-12-20240623
dev-official/andy-CX-18-20240625
.
.
.
// 主要官網開發的分支
dev-official/main-dev 

// 主要官網測試的分支
test/official/main-test

.

.


// 正式環境
release/2020630 <= dev-official/main-dev 