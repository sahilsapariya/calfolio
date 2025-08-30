# CORS Configuration Guide

## Overview
CORS (Cross-Origin Resource Sharing) has been configured for the Calfolio backend with a simple, global configuration that allows all origins, methods, and headers.

## Configuration Files

### Global CORS Configuration
- **File**: `src/main/java/com/example/calfolio/config/CorsConfig.java`
- **Purpose**: Provides global CORS configuration for ALL endpoints
- **Scope**: Applied to `/**` (all paths)

## Current Settings (Fully Permissive)

### Allowed Origins
- `*` (All origins allowed)

### Allowed HTTP Methods
- `*` (All HTTP methods allowed)

### Allowed Headers
- `*` (All headers allowed)

### Additional Settings
- **Credentials**: Enabled
- **Preflight Cache**: 1 hour

## Simple Configuration
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all endpoints
                .allowedOriginPatterns("*")  // Allow all origins
                .allowedMethods("*")  // Allow all HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true)  // Allow credentials
                .maxAge(3600);  // Cache preflight for 1 hour
    }
}
```

## Production Considerations

If you need to restrict CORS in production, you can modify:
```java
registry.addMapping("/**")
        .allowedOriginPatterns("https://yourdomain.com", "https://www.yourdomain.com")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
        .allowedHeaders("Content-Type", "Authorization")
        .allowCredentials(true);
```

## Testing CORS

### Browser Developer Tools
1. Open browser dev tools (F12)
2. Go to Network tab
3. Make requests to your API from a different origin
4. Check for CORS headers in response

### CORS Headers to Look For
- `Access-Control-Allow-Origin`
- `Access-Control-Allow-Methods`
- `Access-Control-Allow-Headers`
- `Access-Control-Allow-Credentials`

### Using curl to Test
```bash
# Test preflight request
curl -X OPTIONS \
  -H "Origin: http://localhost:3000" \
  -H "Access-Control-Request-Method: POST" \
  -H "Access-Control-Request-Headers: Content-Type" \
  http://localhost:8080/api/blogs

# Test actual request
curl -X GET \
  -H "Origin: http://localhost:3000" \
  http://localhost:8080/api/blogs
```

## Troubleshooting

### Common CORS Errors
1. **"CORS policy: No 'Access-Control-Allow-Origin' header"**
   - Check if origin is in allowed origins list
   - Verify CORS configuration is active

2. **"CORS policy: Request header field X is not allowed"**
   - Add required headers to `allowedHeaders` list

3. **"CORS policy: Method Y is not allowed"**
   - Add required method to `allowedMethods` list

### Debug Tips
1. Check browser console for detailed CORS error messages
2. Verify the request origin matches allowed origins exactly
3. Ensure preflight requests (OPTIONS) are handled correctly
4. Test with a simple GET request first

## API Endpoints with CORS Enabled

All these endpoints now support CORS:

- `/check` - Health check endpoint
- `/api/blogs/**` - Blog management
- `/api/users/**` - User management  
- `/api/fin-comic/**` - Financial comic management
- `/api/fin-terms/**` - Financial terms management
- `/api/calculators/**` - Calculator management

## Configuration Precedence

1. **Global Config** (CorsConfig.java) - Applied to all matching paths
2. **Controller Level** (@CrossOrigin on class) - Applied to all methods in controller
3. **Method Level** (@CrossOrigin on method) - Applied to specific method

The most specific configuration takes precedence.
