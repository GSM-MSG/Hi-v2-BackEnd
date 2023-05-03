package team.msg.hiv2.global.security.principle

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import team.msg.hiv2.domain.user.persistence.entity.UserJpaEntity

class AuthDetails(
    private val user: UserJpaEntity
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        user.roles

    override fun getPassword(): String? = null

    override fun getUsername(): String = user.email

    override fun isAccountNonExpired(): Boolean = false

    override fun isAccountNonLocked(): Boolean = false

    override fun isCredentialsNonExpired(): Boolean = false

    override fun isEnabled(): Boolean = false
}