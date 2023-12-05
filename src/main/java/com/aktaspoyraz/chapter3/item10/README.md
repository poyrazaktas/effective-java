# Item 10: Obey the general contract when overriding equals

Equals'ı override ediyorsan çıkabilecek problemleri göze almasın.

Problemlerden uzak kalmanın en güzel yolu, equalsi hiç override etmemek.
Bu durumda `equals` sadece referans eşitliğini kontrol edecektir.

## Bir class için ne zaman default `equals` davranışı yeterli olur?
* Her instance'ı doğası gereği unique ise 
yani instance'ları `value` olarak değil `entity`olarak bir anlam ifade ediyorsa
* İlgili class için bir `logical equality`testine gerek yoksa
* Super class'ın `equals` karşılaştırması yeterliyse
* Class `private` ya da `package-private` ise yani zaten `equals` hiç kullanmayacaksa

## Bir class ne zaman `equals`'i override etmelidir?

`logical equality` testine ihtiyaç duyduğumuz ve bu testin `object identity` üzerinden değil de `value`'lar
üzerinden yapıldığı durumlarda `equals` override edilmelidir.

not: `enum`'ların equals'ı sadece `object identity` üzerinden yapılıyor. dolayısıyla default behaviour yeterli

### General Contract for overriding `equals` method

1. **Reflexive:** `x.equals(x) == true`
2. **Symmetric:** `if x.equals(y) == true then y.equals(x) == true`
3. **Transitive:** `if x.equals(z) == true and z.equals(y) == true then x.equals(y) == true`
4. **Consistent:** `if x.equals(y) == true then (for n times) x.equals(y) == true`
5. **non null:** `x.equals(null) == false`

Bir `subclass`'ın `superclass`'ının `equals` davranışını bozmadan kendi `equals`'nı override etmesi imkansızdır.

Birinci yaklaşım:
```java
  
   @Override
   public boolean equals(Object o) {
       if (!(o instanceof ColorPoint))
          return false;
       return super.equals(o) && ((ColorPoint) o).color == color;
    }
```

```java
    Point p = new Point(1, 2);
    ColorPoint cp = new ColorPoint(1, 2, Color.RED);

```

`p.equals(cp)=true, cp.equals(p) false` : Symmetric olma contract'ını bozuyor

İkinci yaklaşım:

```java
    @Override 
    public boolean equals(Object o) {
        if (!(o instanceof Point))
        return false;
        // If o is a normal Point, do a color-blind comparison
        if (!(o instanceof ColorPoint))
        return o.equals(this);
        // o is a ColorPoint; do a full comparison
        return super.equals(o) && ((ColorPoint) o).color == color;
    }
```

```java
    ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
    Point p2 = new Point(1, 2);
    ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
```

`p1.equals(p2) = true, p2.equals(p3) = true, p1.equals(p3) = false`: Transitive olma contractını bozuyor.

Bu problemi çözmek için `inheritance` yerine `composition` kullanabiliriz.

```java
    // Adds a value component without violating the equals contract
   public class ColorPoint {
      private final Point point;
      private final Color color;
    
      public ColorPoint(int x, int y, Color color) {
         point = new Point(x, y);
         this.color = Objects.requireNonNull(color);
      }
      /**
       * Returns the point-view of this color point.
       */
    public Point asPoint() { 
        return point;
    }
    
  @Override public boolean equals(Object o) {
     if (!(o instanceof ColorPoint))
        return false;
     ColorPoint cp = (ColorPoint) o;
     return cp.point.equals(point) && cp.color.equals(color);
    }
      ...  // Remainder omitted
   }
```

---

## High Quality Equals Method Recipe

1. Use the == operator to check if the argument is a reference to this object.
2. Use the instanceof operator to check if the argument has the correct type. `instanceof` not null kontrolünü de yapar
3. Cast the argument to the correct type.
4. For each “significant” field in the class, check if that field of the argument matches the corresponding field of this object.

* float ve double olmayan primitive type'lar için `==` ile kontrol et 
  * float -> `Float.compare(float, float)`
  * double -> `Double.compare(double, double)`
* eğer array'deki her eleman significant ise `Arrays.equals(arr, arr)`
* attribute'ları karşılaştırırken null pointer almamak için `Objects.equals(obj, obj)`

Not: `equals`'ı her override ettiğinde implementasyonun general contract'a uyuyor mu diye kontrol et ve unit testlerini yaz.

Not: `equals`'ı her override ettiğinde `hashCode`'u da override et.

Not: Google'ın AutoValue framework'u tüm bahsedilen bu contractlara dikkate ederek `equals` ve `hashCode`'u override edebiliyor