package ar.com.oxen.license.api;

public interface Function<T, R> {
	R apply(T t);

	public class Builder<T, R> {
		private Function<T, R> function;

		public static <X, Y> Builder<X, Y> create(Function<X, Y> function) {
			return new Builder<X, Y>(function);
		}

		private Builder(Function<T, R> function) {
			this.function = function;
		}

		public <V> Builder<T, V> andThen(
				final Function<? super R, ? extends V> after) {
			return new Builder<T, V>(new Function<T, V>() {
				@Override
				public V apply(T t) {
					return after.apply(function.apply(t));
				}
			});
		}

		public Function<T, R> build() {
			return function;
		}
	}

}
