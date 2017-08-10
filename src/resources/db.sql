rate表索引：
db.voice.ensureIndex({"vid":-1},{"unique":true,"dropDups":true},{background:true})
db.voice.ensureIndex({"uid":-1,"vid":-1},{background:true})
db.like.ensureIndex({"uid":-1,"vid":-1},{background:true})
db.report.ensureIndex({"uid":-1,"vid":-1},{background:true})
db.recommend.ensureIndex({"type":-1,"timestamp":-1},{background:true})
db.comment.ensureIndex({"vid":-1},{"unique":true,"dropDups":true},{background:true})
db.comment.ensureIndex({"bvid":-1,"likeCount":-1},{background:true})
db.comment.ensureIndex({"bvid":-1,"timestamp":-1},{background:true})
db.block.ensureIndex({"uid":-1},{"unique":true,"dropDups":true},{background:true})
db.statistics.ensureIndex({"vid":-1},{"unique":true,"dropDups":true},{background:true})
 

db.follow.ensureIndex({"uid":-1,"anchorId":-1},{"unique":true,"dropDups":true},{background:true})
db.anchor.ensureIndex({"anchorId":-1},{"unique":true,"dropDups":true},{background:true})
