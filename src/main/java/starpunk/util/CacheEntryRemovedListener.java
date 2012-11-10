package starpunk.util;

/** Called when a cached element is about to be removed. */
public interface CacheEntryRemovedListener<K, V>
{
  void notifyEntryRemoved( K key, V value );
}
