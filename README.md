### SpringBootでDoma2を使用するにあたってのPOCリポジトリ

##### このリポジトリの目的
DomaおよびDoma-Genを利用するにあたって、何ができるのかを整理すること。

##### Doma, Doma-Genとは
DomaはJavaのDBアクセスのフレームワーク。<br>
[Doma公式ドキュメント](https://doma.readthedocs.io/en/2.9.0/)

Doma-Genは、データベースのメタデータからJavaやSQLファイル一式を生成する。<br>
[Doma-Gen公式ドキュメント](https://doma-gen.readthedocs.io/en/2.4.0/#)


#### 前提
検証用のDBはflywayで作成した。下記を参考にした。<br>
[Flyway + MySQL + Docker でマイグレーション環境構築](https://qiita.com/oskamathis/items/04069bc74d9e664705dd)


#### わかったこと

##### Doma-Genの生成元情報
Doma-Genのinputは実際のDBとなること。なので、実際にDB環境を作成した後に、該当テーブルなどを参照させて、対応するdomaのコードを作成する。

##### Doma-Genのやってくれること・やってくれないこと
###### やってくれること
Doma-Genで作成されるコードは下記クラスおよびファイルとなる。
* Dao
    * @SELECT ... domaの仕様により、SELECT文は外部ファイルとしてmustなので、SQLファイルが作成される。
    * @UDPATE ... 主キーによるUPDATE
    * @DELETE ... 主キーによるDELETE
    * @INSERT
* Entity
    * テーブルのカラム情報をもとに、publicなプロティおよびgetter/setterを生成してくれる。
* SQL
    * @SELECTに対応したSQL文が生成される。主キーによる検索SQL文。

###### やってくれないこと
Dao生成時に下記のバルクに関するものは生成してくれない。
* BatchInsertの生成
* BatchUpdateの生成

##### Domaでバルクをする場合の注意
###### Daoの＠BatchInsert/@BatchUpdateの注意点
Daoに@BatchInsertおよび@BatchUpdateメソッドを作成した場合、利用する側からはエンティティのリストを引数として渡すだけで、複数エンティティのINSERT/UPDATEは可能となる。
しかしながら、リストにつめたエンティティの数だけDBにアクセスする。

つまり、100レコードのINSERTをしたい場合に、＠BatchInsertを用いると、DBに100回のINSERTが発生するため、性能懸念が発生するため注意が必要。

###### 一括登録したい場合は、SQL文を自前で記載する
これの対応として、 複数エンティティを@Insertメソッドを用意してそれにBulk文を生成するSQLファイルを紐付ける方法がある。SQLファイル内で複数エンティティをループさせて、一括登録SQLを記載する。

以下のようなイメージ。
```
INSERT INTO ${table} VALUES {$複数エンティティの数だけループ} (エンティティ情報) {$終わるまでループ}
```

なので、Domaのバルクに頼らず、DBへの負荷/性能を考慮した上での使い分けが必要となる。

#### 参考
下記を参考にしました。というか、下記のブログ内に登場するリポジトリをベースに改造しています。<br>
[Spring Boot で Doma 2 を使用するには](http://ksby.hatenablog.com/entry/2015/10/15/043336)


