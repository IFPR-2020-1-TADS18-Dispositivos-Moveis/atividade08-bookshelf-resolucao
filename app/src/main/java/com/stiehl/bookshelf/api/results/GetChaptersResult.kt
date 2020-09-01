package com.stiehl.bookshelf.api.results

import com.stiehl.bookshelf.model.Chapter

data class GetChaptersResult(var chapters: List<Chapter>)