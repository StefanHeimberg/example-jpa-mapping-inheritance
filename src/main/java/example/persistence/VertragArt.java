package example.persistence;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public enum VertragArt {
    
    KARTEVERTRAG(1),
    PFMOBILEVERTRAG(2);
    
    public static final VertragArt valueOf(final Integer code) {
        if(1 == code) {
            return KARTEVERTRAG;
        } else if(2 == code) {
            return PFMOBILEVERTRAG;
        } else {
            throw new IllegalArgumentException("unbekannter code");
        }
    }
    
    private final int code;

    private VertragArt(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
}
