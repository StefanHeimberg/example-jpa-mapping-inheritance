package example.persistence;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public enum VertragArt {
    
    KARTEVERTRAG(1),
    PFMOBILEVERTRAG(2);
    
    private final int code;

    private VertragArt(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
}
