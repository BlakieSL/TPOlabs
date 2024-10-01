    package com.example.lab10.Model;

    import com.example.lab10.Constraint.*;
    import com.example.lab10.Repository.UrlRepository;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;

    @Entity
    public class Url {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String urlId;
        @NotNull
        @Size(min = 5, max = 20)
        private String name;
        @NotNull
        @UrlHttpsDomain
        @UrlUniqueDomain()
        private String targetUrl;
        private String redirectUrl;
        private Integer visits;

        @PasswordSizeDomain()
        @PasswordLowercaseDomain()
        @PasswordUppercaseDomain()
        @PasswordDigitsDomain()
        @PasswordSpecialDomain()
        private String password;


        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUrlId() {
            return urlId;
        }

        public void setUrlId(String urlId) {
            this.urlId = urlId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTargetUrl() {
            return targetUrl;
        }

        public void setTargetUrl(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }

        public Integer getVisits() {
            return visits;
        }

        public void setVisits(Integer visits) {
            this.visits = visits;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
