@RestController
@RequestMapping("/posts")
class PostController(private val viewFollowedPosts: ViewFollowedPosts) {

    @GetMapping("/user/{userId}")
    fun getFollowedPosts(@PathVariable userId: Long): ResponseEntity<List<Post>> {
        val posts = viewFollowedPosts.execute(userId)
        return ResponseEntity.ok(posts)
    }
}
